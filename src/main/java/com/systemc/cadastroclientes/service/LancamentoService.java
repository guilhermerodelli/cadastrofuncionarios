package com.systemc.cadastroclientes.service;

import com.systemc.cadastroclientes.model.Lancamento;
import com.systemc.cadastroclientes.repository.LancamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LancamentoService {

    private final LancamentoRepository repository;

    public LancamentoService(LancamentoRepository repository) {
        this.repository = repository;
    }

    public List<Lancamento> listarPorFuncionario(Long funcionarioId) {
        return repository.findByFuncionarioId(funcionarioId);
    }

    public Lancamento salvar(Lancamento lancamento) {
        return repository.save(lancamento);
    }

    public List<Lancamento> buscarPorPeriodo(
            Long funcionarioId,
            LocalDate inicio,
            LocalDate fim) {

        return repository.findByFuncionarioIdAndDataLancamentoBetween(
                funcionarioId,
                inicio,
                fim
        );
    }
}