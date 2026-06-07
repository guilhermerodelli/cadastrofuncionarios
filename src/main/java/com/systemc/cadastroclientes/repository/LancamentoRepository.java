package com.systemc.cadastroclientes.repository;

import com.systemc.cadastroclientes.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    List<Lancamento> findByFuncionarioId(Long funcionarioId);

    List<Lancamento> findByFuncionarioIdAndDataLancamentoBetween(
            Long funcionarioId,
            LocalDate inicio,
            LocalDate fim
    );
}