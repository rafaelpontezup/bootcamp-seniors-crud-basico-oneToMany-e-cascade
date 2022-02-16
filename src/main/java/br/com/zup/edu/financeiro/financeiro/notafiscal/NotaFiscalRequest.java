package br.com.zup.edu.financeiro.financeiro.notafiscal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class NotaFiscalRequest {

    @NotBlank
    private String numero;

    @NotNull
    @Positive
    private BigDecimal total;

    @Valid
    @NotEmpty
    private List<ItemRequest> itens;

    public NotaFiscalRequest(String numero, BigDecimal total, List<ItemRequest> itens) {
        this.numero = numero;
        this.total = total;
        this.itens = itens;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ItemRequest> getItens() {
        return itens;
    }

    public NotaFiscal toModel() {

        NotaFiscal notaFiscal = new NotaFiscal(this.numero, this.total);
        itens.forEach((i) -> {
           notaFiscal.adiciona(i.toModel());
        });

        return notaFiscal;
    }
}
