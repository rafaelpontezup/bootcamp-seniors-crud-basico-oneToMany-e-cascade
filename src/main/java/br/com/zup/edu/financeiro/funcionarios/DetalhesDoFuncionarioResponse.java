package br.com.zup.edu.financeiro.funcionarios;

public class DetalhesDoFuncionarioResponse {

    private final Long id;
    private final String nome;
    private final Cargo cargo;

    public DetalhesDoFuncionarioResponse(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
        this.cargo = funcionario.getCargo();
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
}
