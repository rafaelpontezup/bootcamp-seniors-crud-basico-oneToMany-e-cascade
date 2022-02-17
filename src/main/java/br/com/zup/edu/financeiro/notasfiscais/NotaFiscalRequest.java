package br.com.zup.edu.financeiro.notasfiscais;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Item> itensDeNota = itens.stream().map((i) -> {
            return i.toModel();
        }).collect(toList());

        return new NotaFiscal(numero, total, itensDeNota);
    }
}
