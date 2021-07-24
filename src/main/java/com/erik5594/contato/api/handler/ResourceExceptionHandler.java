package com.erik5594.contato.api.handler;

import com.erik5594.contato.api.dto.DetalheErro;
import com.erik5594.contato.domain.exception.contato.*;
import com.erik5594.contato.domain.exception.pessoa.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 11:43
 */

@ControllerAdvice
public class ResourceExceptionHandler {


    //HANDLER EXCEPTION PESSOA
    @ExceptionHandler(PessoaNotFoundException.class)
    public ResponseEntity<DetalheErro> handlePessoaNotFoundException
            (PessoaNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(NomePessoaObrigatorioException.class)
    public ResponseEntity<DetalheErro> handleNomePessoaObrigatorioException
            (NomePessoaObrigatorioException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(CpfPessoaObrigatorioException.class)
    public ResponseEntity<DetalheErro> handleCpfPessoaObrigatorioException
            (CpfPessoaObrigatorioException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(CpfPessoaInvalidoException.class)
    public ResponseEntity<DetalheErro> handleCpfPessoaInvalidoException
            (CpfPessoaInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(CpfPessoaJaExisteException.class)
    public ResponseEntity<DetalheErro> handleCpfPessoaJaExisteException
            (CpfPessoaJaExisteException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(DataNascimentoPessoaObrigatorioException.class)
    public ResponseEntity<DetalheErro> handleDataNascimentoPessoaObrigatorioException
            (DataNascimentoPessoaObrigatorioException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(DataNascimentoPessoaInvalidoException.class)
    public ResponseEntity<DetalheErro> handleDataNascimentoPessoaInvalidoException
            (DataNascimentoPessoaInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(ContatoPessoaObrigatorioException.class)
    public ResponseEntity<DetalheErro> handleContatoPessoaObrigatorioException
            (ContatoPessoaObrigatorioException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(PessoaObrigatorioException.class)
    public ResponseEntity<DetalheErro> handlePessoaObrigatorioException
            (PessoaObrigatorioException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(PessoaIdObrigatorioException.class)
    public ResponseEntity<DetalheErro> handlePessoaIdObrigatorioException
            (PessoaIdObrigatorioException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }


    //HANDLER EXCEPTION CONTATO

    @ExceptionHandler(NomeContatoObrigatorioException.class)
    public ResponseEntity<DetalheErro> handleNomeContatoObrigatorioException
            (NomeContatoObrigatorioException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(TelefoneContatoObrigatorioException.class)
    public ResponseEntity<DetalheErro> handleTelefoneContatoObrigatorioException
            (TelefoneContatoObrigatorioException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(TelefoneContatoJaExisteException.class)
    public ResponseEntity<DetalheErro> handleTelefoneContatoJaExisteException
            (TelefoneContatoJaExisteException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(EmailContatoObrigatorioException.class)
    public ResponseEntity<DetalheErro> handleEmailContatoObrigatorioException
            (EmailContatoObrigatorioException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(EmailContatoJaExisteException.class)
    public ResponseEntity<DetalheErro> handleEmailContatoJaExisteException
            (EmailContatoJaExisteException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(EmailContatoInvalidoException.class)
    public ResponseEntity<DetalheErro> handleEmailContatoInvalidoException
            (EmailContatoInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(TelefoneContatoInvalidoException.class)
    public ResponseEntity<DetalheErro> handleTelefoneContatoInvalidoException
            (TelefoneContatoInvalidoException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

    @ExceptionHandler(PessoaContatoObrigatorioException.class)
    public ResponseEntity<DetalheErro> handlePessoaContatoInvalidoException
            (PessoaContatoObrigatorioException e, HttpServletRequest request) {
        return ResponseEntity.status(e.getStatus()).body(new DetalheErro(e.getMensagem(), e.getStatus()));
    }

}
