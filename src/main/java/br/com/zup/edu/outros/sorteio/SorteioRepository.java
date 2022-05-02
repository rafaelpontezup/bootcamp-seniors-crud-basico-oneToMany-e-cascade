package br.com.zup.edu.outros.sorteio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SorteioRepository extends JpaRepository<Sorteio, Long> {
}
