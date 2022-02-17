package br.com.zup.edu.financeiro.notasfiscais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaFiscalRespository extends JpaRepository<NotaFiscal, Long> {
}
