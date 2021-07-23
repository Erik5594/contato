package com.erik5594.contato.domain.exception.contato;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 11:40
 */
public class NomeContatoObrigatorioException extends RuntimeException {

    private final int status = 400;
    private final String mensagem = "Nome do contato é obrigatório.";

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
