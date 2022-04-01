package br.com.zup.edu.seguranca.usuarios;

import javax.persistence.*;

@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UK_usuario_empresa_email", columnNames = { "empresa_id", "email" })
})
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;

    @ManyToOne
    private Empresa empresa; // coluna: empresa_id

    @Deprecated
    public Usuario(){}

    public Usuario(String nome, String cpf, String email, Empresa empresa) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.empresa = empresa;
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

    public String getEmail() {
        return email;
    }

    public Empresa getEmpresa() {
        return empresa;
    }
}
