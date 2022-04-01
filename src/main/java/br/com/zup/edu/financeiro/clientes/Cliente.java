package br.com.zup.edu.financeiro.clientes;

import br.com.zup.edu.utils.CpfUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    @Column(unique = true, length = 64)
    private String hashDoCpf;

    private String profissao;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Deprecated
    public Cliente() {}

    public Cliente(String nome, String cpf, String profissao) {
        this.nome = nome;
        this.cpf = CpfUtils.anonymize(cpf);
        this.hashDoCpf = CpfUtils.hash(cpf);
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
