package br.com.zup.edu.outros.pacotededados;

import br.com.zup.edu.utils.CpfUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false, length = 64, unique = true)
    private String hashDoCpf;

    @Column(nullable = false)
    private String celular;

    @Column(nullable = false)
    private Integer quantidadeEmGigas;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Deprecated
    public Pacote() {}

    public Pacote(String nome, String cpf, String celular, Integer quantidadeEmGigas) {
        this.nome = nome;
        this.cpf = CpfUtils.anonymize(cpf);;
        this.hashDoCpf = CpfUtils.hash(cpf);
        this.celular = celular;
        this.quantidadeEmGigas = quantidadeEmGigas;
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

    public String getHashDoCpf() {
        return hashDoCpf;
    }

    public String getCelular() {
        return celular;
    }

    public Integer getQuantidadeEmGigas() {
        return quantidadeEmGigas;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    // getters
}
