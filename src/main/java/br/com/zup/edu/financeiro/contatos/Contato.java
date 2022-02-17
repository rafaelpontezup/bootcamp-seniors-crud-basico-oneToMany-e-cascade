package br.com.zup.edu.financeiro.contatos;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String empresa;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "contato")
    private List<Telefone> telefones = new ArrayList<>();

    @Deprecated
    public Contato(){}

    public Contato(String nome, String empresa) {
        this.nome = nome;
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void adiciona(Telefone telefone) {
        telefone.setContato(this);
        this.telefones.add(telefone);
    }
}
