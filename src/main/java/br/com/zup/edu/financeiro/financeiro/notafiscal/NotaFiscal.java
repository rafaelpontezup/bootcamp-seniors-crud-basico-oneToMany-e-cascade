package br.com.zup.edu.financeiro.financeiro.notafiscal;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "notaFiscal")
    private List<Item> itens = new ArrayList<>();

    @Deprecated
    public NotaFiscal() {}

    public NotaFiscal(String numero, BigDecimal total) {
        this.numero = numero;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotaFiscal that = (NotaFiscal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void adiciona(Item item) {
        this.itens.add(item); // o pai conhece os filhos
        item.setNotaFiscal(this); // o filho conhece o pai
    }

    public void remove(Item item) {
        this.itens.remove(item);
        item.setNotaFiscal(null);
    }
}
