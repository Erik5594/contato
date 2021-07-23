package com.erik5594.contato.domain.service;

import com.erik5594.contato.domain.entity.Contato;

import java.util.List;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 14:04
 */
public interface ContatoService {

    List<Contato> buscarContatoDaPessoa(Long idPessoa);
    Contato salvar(Contato contato);
    void deletar(Long id);
    void atualizar(Contato contato);

}
