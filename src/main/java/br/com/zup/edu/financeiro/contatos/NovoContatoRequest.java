package br.com.zup.edu.financeiro.contatos;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class NovoContatoRequest {

    @NotBlank
    private String nome;
    private String empresa;

    @Valid
    @NotEmpty
    private List<TelefoneRequest> telefones;

    public NovoContatoRequest(String nome, String empresa, List<TelefoneRequest> telefones) {
        this.nome = nome;
        this.empresa = empresa;
        this.telefones = telefones;
    }

    public String getNome() {
        return nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public List<TelefoneRequest> getTelefones() {
        return telefones;
    }

    public Contato toModel() {
        Contato contato = new Contato(nome, empresa);
        telefones.forEach(t -> {
            contato.adiciona(t.toModel());
        });
        return contato;
    }
}
