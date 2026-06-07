package com.systemc.cadastroclientes.service;

import com.systemc.cadastroclientes.model.Funcionario;
import com.systemc.cadastroclientes.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

private final FuncionarioRepository repository;

public FuncionarioService(FuncionarioRepository repository) {
    this.repository = repository;
}

public List<Funcionario> listarTodos() {
    return repository.findAll();
}

public Funcionario salvar(Funcionario funcionario) {

    if (funcionario.getId() != null) {

        Funcionario existente = repository.findById(funcionario.getId())
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não encontrado"));

        existente.setNome(funcionario.getNome());
        existente.setCpf(funcionario.getCpf());
        existente.setRg(funcionario.getRg());
        existente.setTelefone(funcionario.getTelefone());
        existente.setEmail(funcionario.getEmail());
        existente.setPis(funcionario.getPis());
        existente.setCtps(funcionario.getCtps());
        existente.setDataAdmissao(funcionario.getDataAdmissao());
        existente.setDataDemissao(funcionario.getDataDemissao());
        existente.setFuncao(funcionario.getFuncao());
        existente.setSalario(funcionario.getSalario());
        existente.setObservacao(funcionario.getObservacao());

        return repository.save(existente);
    }

    return repository.save(funcionario);
}

public Funcionario buscarPorId(Long id) {
    return repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Funcionário não encontrado"));
}

public List<Funcionario> buscarPorNome(String nome) {
    return repository.findByNomeContainingIgnoreCase(nome);
}

public void excluir(Long id) {
    repository.deleteById(id);
}

public void salvarObservacao(Long id, String observacao) {

    Funcionario funcionario = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Funcionário não encontrado"));

    funcionario.setObservacao(observacao);

    repository.save(funcionario);
}

}
