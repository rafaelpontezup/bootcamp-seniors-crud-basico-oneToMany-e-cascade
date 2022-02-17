package br.com.zup.edu.financeiro.notasfiscais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovaNotaFiscalController {

    @Autowired
    private NotaFiscalRespository repository;

    @Transactional
    @PostMapping("/api/notas-fiscais")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NotaFiscalRequest request, UriComponentsBuilder uriBuilder) {

        NotaFiscal notaFiscal = request.toModel();
        repository.save(notaFiscal);

        URI location = uriBuilder
                .path("/api/notas-fiscais/{id}")
                .buildAndExpand(notaFiscal.getId()).toUri();

        return ResponseEntity
                .created(location).build();
    }
}
