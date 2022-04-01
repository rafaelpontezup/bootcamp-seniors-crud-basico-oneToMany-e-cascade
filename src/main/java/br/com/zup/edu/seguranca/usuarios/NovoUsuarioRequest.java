package br.com.zup.edu.seguranca.usuarios;

import br.com.zup.edu.financeiro.clientes.Cliente;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoUsuarioRequest {

    @NotBlank
    private String nome;

    @CPF
    @NotBlank
    private String cpf;

    @NotBlank
    private String email;

    @NotNull
    private Long empresaId;

    public NovoUsuarioRequest(String nome, String cpf, String email, Long empresaId) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.empresaId = empresaId;
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

    public Long getEmpresaId() {
        return empresaId;
    }

    public Usuario toModel(EmpresaRepository todasAsEmpresas) {

        Empresa empresa = todasAsEmpresas.findById(empresaId).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "empresa não encontrada");
        });

        return new Usuario(nome, cpf, email, empresa);
    }
}
