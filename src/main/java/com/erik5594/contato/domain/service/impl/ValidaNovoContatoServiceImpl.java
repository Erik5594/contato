package com.erik5594.contato.domain.service.impl;

import com.erik5594.contato.domain.entity.Contato;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 14:54
 */
@Service @Qualifier("novoContato")
public class ValidaNovoContatoServiceImpl extends ValidaContatoServiceImpl {

    @Override
    public void validar(Contato contato) {
        super.validarCamposObrigatorios(contato);
        super.validarTelefone(contato.getNome(), null);
        super.validarEmail(contato.getNome(), null);
    }



}
