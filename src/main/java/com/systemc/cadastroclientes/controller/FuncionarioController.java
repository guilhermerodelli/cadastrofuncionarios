package com.systemc.cadastroclientes.controller;

import com.systemc.cadastroclientes.model.Funcionario;
import com.systemc.cadastroclientes.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(
            @RequestParam(required = false) String busca,
            Model model) {

        if (busca != null && !busca.isBlank()) {
            model.addAttribute(
                    "funcionarios",
                    service.buscarPorNome(busca));
        } else {
            model.addAttribute(
                    "funcionarios",
                    service.listarTodos());
        }

        return "funcionarios";
    }

    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute(
                "funcionario",
                new Funcionario());

        return "funcionario-form";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid @ModelAttribute("funcionario") Funcionario funcionario,
            BindingResult result) {

        if (result.hasErrors()) {
            return "funcionario-form";
        }

        service.salvar(funcionario);

        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}")
    public String detalhes(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "funcionario",
                service.buscarPorId(id));

        return "funcionario-detalhes";
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "funcionario",
                service.buscarPorId(id));

        return "funcionario-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id) {

        service.excluir(id);

        return "redirect:/funcionarios";
    }

    @PostMapping("/salvar-observacao")
    public String salvarObservacao(
            @RequestParam Long id,
            @RequestParam String observacao) {

        service.salvarObservacao(id, observacao);

        return "redirect:/funcionarios/" + id;
    }
}