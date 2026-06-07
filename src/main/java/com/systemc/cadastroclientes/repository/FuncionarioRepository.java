package com.systemc.cadastroclientes.repository;

import com.systemc.cadastroclientes.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByNomeContainingIgnoreCase(String nome);

    Optional<Funcionario> findByCpf(String cpf);

}