package br.com.zup.edu.outros.sorteio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilheteRepository extends JpaRepository<Bilhete, Long> {

    boolean existsByHashDoTelefoneAndNumeroDaSorteAndSorteioId(String hashDoTelefone, Integer numeroDaSorte, Long sorteioId);
}
