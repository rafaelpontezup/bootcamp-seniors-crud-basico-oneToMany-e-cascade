package br.com.zup.edu.outros.reclamacoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamacaoRepository extends JpaRepository<Reclamacao, Long> {

    boolean existsByHashDoTelefoneAndHashDoTexto(String hashDoTelefone, String hashDoTexto);

}
