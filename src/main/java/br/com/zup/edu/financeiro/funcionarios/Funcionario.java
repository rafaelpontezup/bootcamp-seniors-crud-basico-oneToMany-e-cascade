package br.com.zup.edu.financeiro.funcionarios;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.*;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    private BigDecimal salario;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
