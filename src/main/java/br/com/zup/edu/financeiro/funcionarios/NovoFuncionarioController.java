package br.com.zup.edu.financeiro.funcionarios;

import br.com.zup.edu.utils.CpfUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class NovoFuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @Transactional
    @PostMapping("/api/funcionarios")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoFuncionarioRequest request, UriComponentsBuilder uriBuilder) {

        String hashDoCpf = CpfUtils.hash(request.getCpf());
        if (repository.existsByHashDoCpf(hashDoCpf)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "funcionário já existente");
        }

        Funcionario funcionario = request.toModel();
        repository.save(funcionario);

        URI location = uriBuilder
                .path("/api/funcionarios/{id}")
                .buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity
                .created(location).build();
    }

    /**
     * Exception Handler para Unique Constraint de funcionário
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleUniqueConstraintError(ConstraintViolationException exception) {
        Map<String, Object> body = Map.of(
                "message", "funcionário já existente (db)",
                "timestamp", LocalDateTime.now()
        );
        return ResponseEntity
                .unprocessableEntity().body(body);
    }
}
