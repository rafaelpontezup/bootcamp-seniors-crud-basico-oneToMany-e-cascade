package br.com.zup.edu.financeiro.funcionarios;

public class DetalhesDoFuncionarioResponse {

    private Long id;
    private String nome;
    private Cargo cargo;

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
