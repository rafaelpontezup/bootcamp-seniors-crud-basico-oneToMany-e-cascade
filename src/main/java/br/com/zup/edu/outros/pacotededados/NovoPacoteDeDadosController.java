package br.com.zup.edu.outros.pacotededados;

import br.com.zup.edu.utils.CpfUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class NovoPacoteDeDadosController {

    @Autowired
    private PacoteDeDadosRepository repository;

    @Transactional
    @PostMapping("/api/pacotes-de-dados")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoPacoteRequest request,
                                      UriComponentsBuilder uriBuilder) {

        String hashDoCpf = CpfUtils.hash(request.getCpf());
        if (repository.existsByHashDoCpf(hashDoCpf)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "pacote de dados já existente para este CPF");
        }

        Pacote pacote = request.toModel();
        repository.save(pacote);

        URI location = uriBuilder
                .path("/api/pacotes-de-dados/{id}")
                .buildAndExpand(pacote.getId()).toUri();

        return ResponseEntity
                .created(location).build();
    }

    /**
     * Controller Advice Local
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e, WebRequest request) {

        Map<String, Object> body = Map.of(
                "status", 422,
                "error", "Unprocessable Entity",
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", "pacote de dados já existente para este CPF"
        );

        return ResponseEntity
                .unprocessableEntity().body(body);
    }
}
