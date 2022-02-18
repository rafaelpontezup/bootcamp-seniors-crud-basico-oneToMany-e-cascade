package br.com.zup.edu.financeiro.movimentacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RemoveCategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @DeleteMapping("/api/categorias/{id}")
    public ResponseEntity<?> remove(Long id) {

        Categoria categoria = repository.findById(id).orElseThrow(() -> {
           return new ResponseStatusException(HttpStatus.NOT_FOUND, "categoria não encontrada");
        });

        repository.delete(categoria);

        return ResponseEntity
                .noContent().build();
    }
}
