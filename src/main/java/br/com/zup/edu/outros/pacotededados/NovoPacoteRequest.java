package br.com.zup.edu.outros.pacotededados;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NovoPacoteRequest {

    @NotBlank
    private String nome;

    @CPF
    @NotBlank
    private String cpf;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
    private String celular;

    @NotBlank
    @Size(min = 5, max = 50)
    private Integer quantidadeEmGigas;

    public NovoPacoteRequest(String nome, String cpf, String celular, Integer quantidadeEmGigas) {
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.quantidadeEmGigas = quantidadeEmGigas;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCelular() {
        return celular;
    }

    public Integer getQuantidadeEmGigas() {
        return quantidadeEmGigas;
    }

    public Pacote toModel() {
        return new Pacote(nome, cpf, celular, quantidadeEmGigas);
    }
}
