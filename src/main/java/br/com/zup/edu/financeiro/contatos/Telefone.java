package br.com.zup.edu.financeiro.contatos;

import javax.persistence.*;

@Entity
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String numero;

    @ManyToOne
    private Contato contato;

    @Deprecated
    public Telefone(){}

    public Telefone(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }
}