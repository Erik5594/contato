package com.erik5594.contato.domain.exception.pessoa;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 11:40
 */
public class PessoaNotFoundException extends RuntimeException {

    private final int status = 404;
    private final String mensagem = "Pessoa não encontrada.";

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
