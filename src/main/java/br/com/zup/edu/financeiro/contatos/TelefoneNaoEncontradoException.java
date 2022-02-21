package br.com.zup.edu.financeiro.contatos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "telefone não encontrado")
public class TelefoneNaoEncontradoException extends RuntimeException {

    public TelefoneNaoEncontradoException(String message) {
        super(message);
    }
}
