package com.erik5594.contato.domain.exception.pessoa;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 11:40
 */
public class PessoaObrigatorioException extends RuntimeException {

    private final int status = 400;
    private final String mensagem = "Os atributos de pessoas devem ser informados.";

    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }
}
