package br.com.zup.edu.outros.sorteio;

import br.com.zup.edu.utils.Encrypter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NovaBilheteRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
    private String telefone;

    @NotNull
    private Long sorteioId;

    @NotNull
    @Size(min = 1, max = 9999)
    private Integer numeroDaSorte;

    public NovaBilheteRequest(String nome, String telefone, Long sorteioId, Integer numeroDaSorte) {
        this.nome = nome;
        this.telefone = telefone;
        this.sorteioId = sorteioId;
        this.numeroDaSorte = numeroDaSorte;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Long getSorteioId() {
        return sorteioId;
    }

    public Integer getNumeroDaSorte() {
        return numeroDaSorte;
    }

    public String getHashDoTelefone() {
        return Encrypter.hash(telefone);
    }

    public Bilhete toModel(SorteioRepository todosOsSorteios) {

        Sorteio sorteio = todosOsSorteios.findById(sorteioId).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "sorteio inválido ou não encontrado");
        });

        return new Bilhete(nome, telefone, numeroDaSorte, sorteio);
    }
}
