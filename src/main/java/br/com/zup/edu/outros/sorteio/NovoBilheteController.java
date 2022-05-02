package br.com.zup.edu.outros.sorteio;

import br.com.zup.edu.outros.reclamacoes.NovaReclamacaoRequest;
import br.com.zup.edu.outros.reclamacoes.Reclamacao;
import br.com.zup.edu.outros.reclamacoes.ReclamacaoRepository;
import br.com.zup.edu.utils.Encrypter;
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
public class NovoBilheteController {

    @Autowired
    private BilheteRepository repository;

    @Autowired
    private SorteioRepository sorteioRepository;

    @Transactional
    @PostMapping("/api/bilhetes")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovaBilheteRequest request,
                                      UriComponentsBuilder uriBuilder) {

        if (repository.existsByHashDoTelefoneAndNumeroDaSorteAndSorteioId(
                request.getHashDoTelefone(), request.getNumeroDaSorte(), request.getSorteioId()
        )) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "bilhete já existente para este sorteio");
        }

        Bilhete bilhete = request.toModel(sorteioRepository);
        repository.save(bilhete);

        URI location = uriBuilder
                .path("/api/bilhetes/{id}")
                .buildAndExpand(bilhete.getId()).toUri();

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
                "message", "bilhete já existente para este sorteio"
        );

        return ResponseEntity
                .unprocessableEntity().body(body);
    }
}
