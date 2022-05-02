package br.com.zup.edu.outros.reclamacoes;

import br.com.zup.edu.outros.pacotededados.NovoPacoteRequest;
import br.com.zup.edu.outros.pacotededados.Pacote;
import br.com.zup.edu.outros.pacotededados.PacoteDeDadosRepository;
import br.com.zup.edu.utils.CpfUtils;
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
public class NovaReclamacaoController {

    @Autowired
    private ReclamacaoRepository repository;

    @Transactional
    @PostMapping("/api/reclamacoes")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovaReclamacaoRequest request,
                                      UriComponentsBuilder uriBuilder) {

        String hashDoTelefone = Encrypter.hash(request.getTelefone());
        String hashDoTexto = Encrypter.hash(request.getTexto());
        if (repository.existsByHashDoTelefoneAndHashDoTexto(hashDoTelefone, hashDoTexto)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "reclamação já existente para este telefone");
        }

        Reclamacao reclamacao = request.toModel();
        repository.save(reclamacao);

        URI location = uriBuilder
                .path("/api/reclamacoes/{id}")
                .buildAndExpand(reclamacao.getId()).toUri();

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
                "message", "reclamação já existente para este telefone"
        );

        return ResponseEntity
                .unprocessableEntity().body(body);
    }
}
