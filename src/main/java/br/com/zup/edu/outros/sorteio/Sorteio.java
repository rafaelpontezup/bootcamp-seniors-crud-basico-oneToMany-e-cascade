package br.com.zup.edu.outros.sorteio;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Sorteio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String descricao;

    @Future
    @NotNull
    @Column(nullable = false)
    private LocalDate dataDoSorteio;

    @Deprecated
    public Sorteio(){}

    public Sorteio(String descricao, LocalDate dataDoSorteio) {
        this.descricao = descricao;
        this.dataDoSorteio = dataDoSorteio;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataDoSorteio() {
        return dataDoSorteio;
    }
}
