package br.com.zup.edu.outros.sorteio;

import br.com.zup.edu.utils.Encrypter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_bilhete_fone_numero_sorteio_id",
                columnNames = { "hash_telefone", "numero_sorte", "sorteio_id" }
        )
})
@Entity
public class Bilhete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "hash_telefone", nullable = false, length = 64)
    private String hashDoTelefone;

    @Column(name = "numero_sorte", nullable = false)
    private Integer numeroDaSorte;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sorteio_id", nullable = false)
    private Sorteio sorteio;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Deprecated
    public Bilhete(){}

    public Bilhete(String nome, String telefone, Integer numeroDaSorte, Sorteio sorteio) {
        this.nome = nome;
        this.hashDoTelefone = Encrypter.hash(telefone);
        this.numeroDaSorte = numeroDaSorte;
        this.sorteio = sorteio;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getHashDoTelefone() {
        return hashDoTelefone;
    }

    public Integer getNumeroDaSorte() {
        return numeroDaSorte;
    }

    public Sorteio getSorteio() {
        return sorteio;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
