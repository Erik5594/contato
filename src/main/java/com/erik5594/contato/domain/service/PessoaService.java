package com.erik5594.contato.domain.service;

import com.erik5594.contato.domain.entity.Pessoa;

import java.util.List;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 20:49
 */
public interface PessoaService {

    List<Pessoa> listarTodasPessoas();
    Pessoa criarPessoa(Pessoa pessoa);

}
