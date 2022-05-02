package br.com.zup.edu.outros.reclamacoes;

import br.com.zup.edu.utils.CpfUtils;
import br.com.zup.edu.utils.Encrypter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_reclamacao_telefone_texto", columnNames = { "hash_telefone", "hash_texto" })
})
@Entity
public class Reclamacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(name = "hash_telefone", nullable = false, length = 64)
    private String hashDoTelefone;

    @Column(nullable = false, length = 4000)
    private String texto;

    @Column(name = "hash_texto", nullable = false, length = 64)
    private String hashDoTexto;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Deprecated
    public Reclamacao(){}

    public Reclamacao(String nome, String email, String telefone, String texto) {
        this.nome = nome;
        this.email = email;
        this.hashDoTelefone = Encrypter.hash(telefone);
        this.hashDoTexto = Encrypter.hash(texto);
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public String getEmail() {
        return email;
    }

    public String getHashDoTelefone() {
        return hashDoTelefone;
    }
}
