package com.erik5594.contato.domain.service.impl;

import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.exception.contato.*;
import com.erik5594.contato.domain.exception.pessoa.ContatoPessoaObrigatorioException;
import com.erik5594.contato.domain.repository.ContatoRepository;
import com.erik5594.contato.domain.service.ValidaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author erik_
 * Data Criacao: 23/07/2021 - 08:39
 */
@Service @Qualifier("validaContato")
public class ValidaContatoServiceImpl implements ValidaService<Contato> {

    private static final int QTDE_MIN_DIGITOS_TELEFONE = 10;

    @Autowired
    private ContatoRepository repository;

    @Override
    public void validar(Contato contato) {
        this.validarCamposObrigatorios(contato);
        this.validarTelefone(contato.getTelefone());
        this.validarEmail(contato.getEmail());
    }

    private void validarCamposObrigatorios(Contato contato){
        if(contato == null)
            throw new ContatoPessoaObrigatorioException();

        if(StringUtils.isBlank(contato.getNome()))
            throw new NomeContatoObrigatorioException();

        if(StringUtils.isBlank(contato.getTelefone()))
            throw new TelefoneContatoObrigatorioException();

        if(StringUtils.isBlank(contato.getEmail()))
            throw new EmailContatoObrigatorioException();
    }

    private void validarTelefone(String telefone){
        if(telefone.length() < QTDE_MIN_DIGITOS_TELEFONE)
            throw new TelefoneContatoInvalidoException();

        List<Contato> contatos = repository.findByTelefoneEquals(telefone);
        if (contatos != null && !contatos.isEmpty())
            throw new TelefoneContatoJaExisteException(String.format("Telefone '%s' já está cadastrado para outra pessoa.", telefone));
    }

    private void validarEmail(String email){
        if(!email.contains("@"))
            throw new EmailContatoInvalidoException();

        List<Contato> contatos = repository.findByEmailEquals(email);
        if (contatos != null && !contatos.isEmpty())
            throw new EmailContatoJaExisteException(String.format("Email '%s' já está cadastrado para outra pessoa.", email));
    }

    public void setRepository(ContatoRepository repository) {
        this.repository = repository;
    }
}
