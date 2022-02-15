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

    @Transactional
    @PostMapping("/api/notas-fiscais")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NotaFiscalRequest request, UriComponentsBuilder uriBuilder) {

        // TODO: implementar logica de negocio

        Long notaId = -999999L;
        URI location = uriBuilder.path("/api/notas-fiscais/{id}")
                            .buildAndExpand(notaId).toUri();

        return ResponseEntity
                .created(location).build();
    }

    @Transactional
    @DeleteMapping("/api/notas-fiscais/{notaId}")
    public ResponseEntity<?> remove(@PathVariable("notaId") Long notaId) {

        // TODO: implementar logica de negocio

        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("/api/notas-fiscais/{notaId}/itens/{itemId}")
    public ResponseEntity<?> removeItem(@PathVariable("notaId") Long notaId, @PathVariable("itemId") Long itemId) {

        // TODO: implementar logica de negocio

        return ResponseEntity.ok().build();
    }
}
