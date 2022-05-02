package br.com.zup.edu.outros.pacotededados;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacoteDeDadosRepository extends JpaRepository<Pacote, Long> {

    boolean existsByHashDoCpf(String hashDoCpf);
}
