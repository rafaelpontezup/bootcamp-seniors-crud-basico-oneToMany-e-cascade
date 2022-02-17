package br.com.zup.edu.financeiro.notasfiscais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RemoveNotaFiscalController {

    @Autowired
    private NotaFiscalRespository repository;

    @Transactional
    @DeleteMapping("/api/notas-fiscais/{notaId}")
    public ResponseEntity<?> remove(@PathVariable("notaId") Long notaId) {

        NotaFiscal notaFiscal = repository.findById(notaId).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "nota fiscal não encontrada");
        });

        repository.delete(notaFiscal);

        return ResponseEntity
                .noContent().build();
    }
}
