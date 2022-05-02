package br.com.zup.edu.outros.reclamacoes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NovaReclamacaoRequest {

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
    private String telefone;

    @NotBlank
    @Size(max = 4000)
    private String texto;

    public NovaReclamacaoRequest(String nome, String email, String telefone, String texto) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.texto = texto;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getTexto() {
        return texto;
    }

    public Reclamacao toModel() {
        return new Reclamacao(nome, email, telefone, texto);
    }
}
