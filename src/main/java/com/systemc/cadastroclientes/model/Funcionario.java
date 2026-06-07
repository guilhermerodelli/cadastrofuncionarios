package com.systemc.cadastroclientes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @NotBlank(message = "CPF obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 números")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "RG obrigatório")
    @Pattern(regexp = "\\d+", message = "RG deve conter apenas números")
    private String rg;

    @NotBlank(message = "Telefone obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone deve conter 10 ou 11 números")
    private String telefone;

    @NotBlank(message = "E-mail obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "PIS obrigatório")
    @Pattern(regexp = "\\d{11}", message = "PIS deve conter 11 números")
    private String pis;

    @NotBlank(message = "CTPS obrigatória")
    @Pattern(regexp = "\\d+", message = "CTPS deve conter apenas números")
    private String ctps;

    // REMOVA O @NotNull CASO A DATA NÃO SEJA OBRIGATÓRIA
    private LocalDate dataAdmissao;

    private LocalDate dataDemissao;

    @NotBlank(message = "Função obrigatória")
    private String funcao;

    @NotNull(message = "Salário obrigatório")
    @DecimalMin(value = "0.01", message = "Salário deve ser maior que zero")
    private BigDecimal salario;

    @Column(columnDefinition = "TEXT")
    private String observacao;

   @OneToMany(
        mappedBy = "funcionario",
        cascade = CascadeType.ALL
    )
    private List<Lancamento> lancamentos;

    public Funcionario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(LocalDate dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }
}