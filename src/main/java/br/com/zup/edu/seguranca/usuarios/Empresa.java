package br.com.zup.edu.seguranca.usuarios;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;

    @Deprecated
    public Empresa(){}

    public Empresa(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }
}
