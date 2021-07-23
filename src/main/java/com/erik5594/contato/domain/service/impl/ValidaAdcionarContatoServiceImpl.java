package com.erik5594.contato.domain.service.impl;

import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.entity.Pessoa;
import com.erik5594.contato.domain.exception.contato.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 14:54
 */
@Service @Qualifier("adcionarContato")
public class ValidaAdcionarContatoServiceImpl extends ValidaContatoServiceImpl {

    @Override
    public void validar(Contato contato) {
        this.validarCamposObrigatorios(contato);
        this.validarPessoa(contato.getPessoa());
        this.validarNome(contato.getNome(), contato.getPessoa().getId());
        this.validarTelefone(contato.getNome(), contato.getPessoa().getId());
        this.validarEmail(contato.getNome(), contato.getPessoa().getId());
    }

    private void validarPessoa(Pessoa pessoa){
        if(pessoa == null || (pessoa.getId() == null || pessoa.getId() < 1))
            throw new PessoaContatoObrigatorioException();
    }

}
