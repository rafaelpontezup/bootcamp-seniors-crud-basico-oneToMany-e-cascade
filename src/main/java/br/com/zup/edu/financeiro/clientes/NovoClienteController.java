package br.com.zup.edu.financeiro.clientes;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class NovoClienteController {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    @PostMapping("/api/clientes")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoClienteRequest request,
                                      UriComponentsBuilder uriBuilder) {

        if (repository.existsByCpf(request.getCpf())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "cliente já existente no sistema");
        }

        Cliente cliente = request.toModel();
        repository.save(cliente);

        URI location = uriBuilder
                .path("/api/clientes/{id}")
                .buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity
                .created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e) {

        Map<String, Object> body = Map.of(
                "message", "cliente já existente no sistema",
                "timestamp", LocalDateTime.now()
        );

        return ResponseEntity
                .unprocessableEntity().body(body);
    }
}
