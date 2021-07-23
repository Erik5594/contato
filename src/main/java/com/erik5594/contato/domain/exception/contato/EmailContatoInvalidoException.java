package com.erik5594.contato.domain.exception.contato;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 11:40
 */
public class EmailContatoInvalidoException extends RuntimeException {

    private final int status = 400;
    private String mensagem = "Email informado é inválido.";

    public EmailContatoInvalidoException() {
    }

    public EmailContatoInvalidoException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
