package br.com.zup.edu.financeiro.clientes;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

public class NovoClienteRequest {

    @NotBlank
    private String nome;

    @CPF
    @NotBlank
    private String cpf;

    @NotBlank
    private String profissao;

    public NovoClienteRequest(String nome, String cpf, String profissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.profissao = profissao;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getProfissao() {
        return profissao;
    }

    public Cliente toModel() {
        return new Cliente(nome, cpf, profissao);
    }
}
