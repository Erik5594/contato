package com.erik5594.contato.domain.exception.contato;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 11:40
 */
public class EmailContatoJaExisteException extends RuntimeException {

    private final int status = 400;
    private String mensagem = "Email de contato já cadastrado.";

    public EmailContatoJaExisteException() {
    }

    public EmailContatoJaExisteException(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
