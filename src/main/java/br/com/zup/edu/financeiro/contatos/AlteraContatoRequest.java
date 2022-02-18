package br.com.zup.edu.financeiro.contatos;

import javax.validation.constraints.NotBlank;

public class AlteraContatoRequest {

    @NotBlank
    private String nome;
    private String empresa;

    public AlteraContatoRequest(String nome, String empresa) {
        this.nome = nome;
        this.empresa = empresa;
    }

    public String getNome() {
        return nome;
    }

    public String getEmpresa() {
        return empresa;
    }
}
