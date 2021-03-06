package com.erik5594.contato.domain.service.impl;

import com.erik5594.contato.domain.entity.Pessoa;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 14:08
 */

@Qualifier("novaPessoa")
@Service
public class ValidaNovaPessoaServiceImpl extends ValidaPessoaServiceImpl {

    @Override
    public void validar(Pessoa pessoa) {
        this.validarCamposObrigatorios(pessoa);
        this.validarCpf(pessoa.getCpf());
        this.validarJaExisteCpf(pessoa.getCpf());
        this.validarDataNascimento(pessoa.getDataNascimento());
    }

}
