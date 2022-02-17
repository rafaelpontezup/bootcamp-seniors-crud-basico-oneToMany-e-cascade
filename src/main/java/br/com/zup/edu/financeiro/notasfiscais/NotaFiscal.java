package br.com.zup.edu.financeiro.notasfiscais;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Item> itens;

    @Deprecated
    public NotaFiscal(){}

    public NotaFiscal(String numero, BigDecimal total, List<Item> itens) {
        this.numero = numero;
        this.total = total;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItens() {
        return itens;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
