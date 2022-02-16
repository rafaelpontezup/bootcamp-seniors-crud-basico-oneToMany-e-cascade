package br.com.zup.edu.financeiro.financeiro.notafiscal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class NotaFiscalController {

    @Autowired
    private NotaFiscalRepository repository;

    @Transactional
    @PostMapping("/api/notas-fiscais")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NotaFiscalRequest request, UriComponentsBuilder uriBuilder) {

        NotaFiscal notaFiscal = request.toModel();
        repository.save(notaFiscal);

        Long notaId = notaFiscal.getId();
        URI location = uriBuilder.path("/api/notas-fiscais/{id}")
                            .buildAndExpand(notaId).toUri();

        return ResponseEntity
                .created(location).build();
    }

    @Transactional
    @DeleteMapping("/api/notas-fiscais/{notaId}")
    public ResponseEntity<?> remove(@PathVariable("notaId") Long notaId) {

        NotaFiscal notaFiscal = repository.findById(notaId).orElseThrow(() -> {
           return new ResponseStatusException(HttpStatus.NOT_FOUND, "nota fiscal não encontrada");
        });

        repository.delete(notaFiscal);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("/api/notas-fiscais/{notaId}/itens/{itemId}")
    public ResponseEntity<?> removeItem(@PathVariable("notaId") Long notaId, @PathVariable("itemId") Long itemId) {

        NotaFiscal notaFiscal = repository.findById(notaId).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "nota fiscal não encontrada");
        });

        Item item = new Item(itemId); // DETACHED
        notaFiscal.remove(item);

        return ResponseEntity.ok().build();
    }
}
