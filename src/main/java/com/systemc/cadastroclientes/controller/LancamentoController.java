package com.systemc.cadastroclientes.controller;

import com.systemc.cadastroclientes.model.Funcionario;
import com.systemc.cadastroclientes.model.Lancamento;
import com.systemc.cadastroclientes.service.FuncionarioService;
import com.systemc.cadastroclientes.service.LancamentoService;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/funcionarios/{funcionarioId}/lancamentos")
public class LancamentoController {

private final LancamentoService lancamentoService;
private final FuncionarioService funcionarioService;

public LancamentoController(
        LancamentoService lancamentoService,
        FuncionarioService funcionarioService) {

    this.lancamentoService = lancamentoService;
    this.funcionarioService = funcionarioService;
}

@GetMapping
public String listar(
        @PathVariable Long funcionarioId,
        Model model) {

    Funcionario funcionario =
            funcionarioService.buscarPorId(funcionarioId);

    model.addAttribute("funcionario", funcionario);

    model.addAttribute(
            "lancamentos",
            lancamentoService.listarPorFuncionario(funcionarioId));

    return "lancamentos";
}

@GetMapping("/novo")
public String novo(
        @PathVariable Long funcionarioId,
        Model model) {

    Funcionario funcionario =
            funcionarioService.buscarPorId(funcionarioId);

    Lancamento lancamento = new Lancamento();
    lancamento.setFuncionario(funcionario);

    model.addAttribute("lancamento", lancamento);

    return "lancamento-form";
}

@PostMapping("/salvar")
public String salvar(
        @PathVariable Long funcionarioId,
        @ModelAttribute Lancamento lancamento) {

    Funcionario funcionario =
            funcionarioService.buscarPorId(funcionarioId);

    lancamento.setFuncionario(funcionario);

    lancamentoService.salvar(lancamento);

    return "redirect:/funcionarios/"
            + funcionarioId
            + "/lancamentos";
}

//PDF
@GetMapping("/pdf")
public void gerarPdf(
        @PathVariable Long funcionarioId,
        HttpServletResponse response) throws Exception {

    Funcionario funcionario =
            funcionarioService.buscarPorId(funcionarioId);

    List<Lancamento> lancamentos =
            lancamentoService.listarPorFuncionario(funcionarioId);

    response.setContentType("application/pdf");

    response.setHeader(
            "Content-Disposition",
            "inline; filename=relatorio-funcionario.pdf");

    Document document = new Document();

    PdfWriter.getInstance(
            document,
            response.getOutputStream());

    document.open();

    // CABEÇALHO DA EMPRESA

    document.add(new Paragraph("TESTE RODELLI SYSTEM"));
    document.add(new Paragraph("CNPJ: 00.000.000/0001-00"));
    document.add(new Paragraph("Rua Teste, 123 - São Paulo/SP"));
    document.add(new Paragraph("Telefone: (11) 99999-9999"));
    document.add(new Paragraph("E-mail: teste@empresa.com.br"));

    document.add(new Paragraph(" "));
    document.add(new Paragraph("==================================================="));
    document.add(new Paragraph("RELATÓRIO DE LANÇAMENTOS DE FUNCIONÁRIO"));
    document.add(new Paragraph("==================================================="));

    document.add(new Paragraph(" "));

    // DADOS DO FUNCIONÁRIO

    document.add(new Paragraph(
            "Funcionário: " + funcionario.getNome()));

    document.add(new Paragraph(
            "CPF: " + funcionario.getCpf()));

    document.add(new Paragraph(
            "Função: " + funcionario.getFuncao()));

    document.add(new Paragraph(" "));
    document.add(new Paragraph("LANÇAMENTOS"));
    document.add(new Paragraph(" "));

    BigDecimal total = BigDecimal.ZERO;

    for (Lancamento l : lancamentos) {

        document.add(new Paragraph(
                "Data Lançamento: "
                        + l.getDataLancamento()));

        document.add(new Paragraph(
                "Data Pagamento: "
                        + l.getDataPagamento()));

        document.add(new Paragraph(
                "Histórico: "
                        + l.getHistorico()));

        document.add(new Paragraph(
                "Valor: R$ "
                        + l.getValor()));

        document.add(new Paragraph(
                "---------------------------------------------------"));

        total = total.add(l.getValor());
    }

    document.add(new Paragraph(" "));

    document.add(new Paragraph(
            "TOTAL GERAL: R$ " + total));

    document.close();
}

}
