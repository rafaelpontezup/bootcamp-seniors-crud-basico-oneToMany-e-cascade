package br.com.zup.edu.seguranca.usuarios;


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
public class NovoUsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    @PostMapping("/api/usuarios")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoUsuarioRequest request,
                                      UriComponentsBuilder uriBuilder) {

        if (repository.existsByEmailAndEmpresaId(request.getEmail(), request.getEmpresaId())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                    "usuário com este email já existente para esta empresa");
        }

        Usuario usuario = request.toModel(empresaRepository);
        repository.save(usuario);

        URI location = uriBuilder
                .path("/api/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity
                .created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e) {

        Map<String, Object> body = Map.of(
                "message", "usuário com este email já existente para esta empresa",
                "timestamp", LocalDateTime.now()
        );

        return ResponseEntity
                .unprocessableEntity().body(body);
    }
}
