package br.com.zup.edu.financeiro.funcionarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class NovoFuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @Transactional
    @PostMapping("/api/funcionarios")
    public ResponseEntity<?> cadastra(@RequestBody @Valid NovoFuncionarioRequest request, UriComponentsBuilder uriBuilder) {

        Funcionario funcionario = request.toModel();
        repository.save(funcionario);

        URI location = uriBuilder
                .path("/api/funcionarios/{id}")
                .buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity
                .created(location).build();
    }
}
