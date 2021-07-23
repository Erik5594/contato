package com.erik5594.contato.domain.service.impl;

import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.exception.contato.*;
import com.erik5594.contato.domain.exception.pessoa.ContatoPessoaObrigatorioException;
import com.erik5594.contato.domain.repository.ContatoRepository;
import com.erik5594.contato.domain.service.ValidaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author erik_
 * Data Criacao: 23/07/2021 - 08:39
 */
@Service
public abstract class ValidaContatoServiceImpl implements ValidaService<Contato> {

    private static final int QTDE_MIN_DIGITOS_TELEFONE = 10;

    @Autowired
    private ContatoRepository repository;

    void validarCamposObrigatorios(Contato contato){
        if(contato == null)
            throw new ContatoPessoaObrigatorioException();

        if(StringUtils.isBlank(contato.getNome()))
            throw new NomeContatoObrigatorioException();

        if(StringUtils.isBlank(contato.getTelefone()))
            throw new TelefoneContatoObrigatorioException();

        if(StringUtils.isBlank(contato.getEmail()))
            throw new EmailContatoObrigatorioException();
    }

    void validarNome(String nome, Long idPessoa){
        if(repository.findByNomeEqualsAndPessoa_IdEquals(nome, idPessoa) != null)
            throw new NomeContatoJaExisteException();
    }

    void validarTelefone(String telefone, Long idPessoa){
        if(telefone.length() < QTDE_MIN_DIGITOS_TELEFONE)
            throw new TelefoneContatoInvalidoException();
        if(idPessoa == null) {
            if (repository.findByTelefoneEquals(telefone) != null)
                throw new TelefoneContatoJaExisteException("Telefone de contato já cadastrado para outra pessoa.");
        }else {
            if (repository.findByTelefoneEqualsAndPessoa_IdEquals(telefone, idPessoa) != null)
                throw new TelefoneContatoJaExisteException();
        }
    }

    void validarEmail(String email, Long idPessoa){
        if(!email.contains("@"))
            throw new EmailContatoInvalidoException();
        if(idPessoa == null) {
            if (repository.findByEmailEquals(email) != null)
                throw new EmailContatoJaExisteException("Email de contato já cadastrado para outra pessoa.");
        }else{
            if(repository.findByEmailEqualsAndPessoa_IdEquals(email, idPessoa) != null)
                throw new EmailContatoJaExisteException();
        }
    }
}
