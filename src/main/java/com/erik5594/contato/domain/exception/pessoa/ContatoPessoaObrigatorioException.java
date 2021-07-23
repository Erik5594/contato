package com.erik5594.contato.domain.exception.pessoa;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 11:40
 */
public class ContatoPessoaObrigatorioException extends RuntimeException {

    private final int status = 400;
    private final String mensagem = "Deve ser informado pelo menos 1 contato.";

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
