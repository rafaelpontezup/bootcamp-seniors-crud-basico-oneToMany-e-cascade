package br.com.zup.edu.financeiro.clientes;

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
public class NovoClienteController {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    @PostMapping("/api/clientes")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoClienteRequest request,
                                      UriComponentsBuilder uriBuilder) {

        Cliente cliente = request.toModel();
        repository.save(cliente);

        URI location = uriBuilder
                .path("/api/clientes/{id}")
                .buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity
                .created(location).build();
    }
}
