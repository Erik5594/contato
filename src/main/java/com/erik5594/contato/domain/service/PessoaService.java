package com.erik5594.contato.domain.service;

import com.erik5594.contato.domain.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 20:49
 */
public interface PessoaService {

    Pessoa buscar(Long id);
    Page<Pessoa> listarTodasPessoas(Pessoa pessoa, Pageable pageable);
    Pessoa salvar(Pessoa pessoa);
    void atualizar(Pessoa pessoa);
    void deletar(Long id);

}
