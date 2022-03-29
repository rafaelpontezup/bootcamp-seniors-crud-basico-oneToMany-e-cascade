package br.com.zup.edu.financeiro.clientes;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String cpf;
    private String profissao;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Deprecated
    public Cliente() {}

    public Cliente(String nome, String cpf, String profissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.profissao = profissao;
    }

    public Long getId() {
        return id;
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
    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
