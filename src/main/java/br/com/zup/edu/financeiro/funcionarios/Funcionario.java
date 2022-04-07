package br.com.zup.edu.financeiro.funcionarios;

import br.com.zup.edu.utils.CpfUtils;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.*;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false, unique = true, length = 64)
    private String hashDoCpf;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    private BigDecimal salario;

    @Deprecated
    public Funcionario(){}

    public Funcionario(String nome, String cpf, Cargo cargo, BigDecimal salario) {
        this.nome = nome;
        this.cpf = CpfUtils.anonymize(cpf);
        this.hashDoCpf = CpfUtils.hash(cpf);
        this.cargo = cargo;
        this.salario = salario;
    }

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

    public String getCpf() {
        return cpf;
    }
}
