package br.com.zup.edu.financeiro.contatos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class TelefoneController {

    @Autowired
    private ContatoRepository repository;

    @Transactional
    @PostMapping("/api/contatos/{contatoId}/telefones")
    public ResponseEntity<?> adicionaTelefone(@PathVariable("contatoId") Long contatoId,
                                              @RequestBody @Valid NovoTelefoneRequest request,
                                              UriComponentsBuilder uriBuilder) {

        Contato contato = repository.findById(contatoId).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "contato não encontrado");
        });

        Telefone telefone = request.toModel();
        contato.adiciona(telefone);

        URI location = uriBuilder
                .path("/api/contatos/{id}")
                .buildAndExpand(contato.getId()).toUri();

        return ResponseEntity
                .created(location).build();
    }

    @Transactional
    @DeleteMapping("/api/contatos/{contatoId}/telefones/{telefoneId}")
    public ResponseEntity<?> removeTelefone(@PathVariable("contatoId") Long contatoId,
                                            @PathVariable("telefoneId") Long telefoneId) {

        Contato contato = repository.findById(contatoId).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "contato não encontrado");
        });

        contato.remove(new Telefone(telefoneId));
        repository.save(contato); // não necessário por causa do contexto de persistência

        return ResponseEntity
                .noContent().build();
    }
}
