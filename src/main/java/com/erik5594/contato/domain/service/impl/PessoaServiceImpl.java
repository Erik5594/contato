package com.erik5594.contato.domain.service.impl;

import com.erik5594.contato.domain.entity.Pessoa;
import com.erik5594.contato.domain.repository.PessoaRepository;
import com.erik5594.contato.domain.service.PessoaService;
import com.erik5594.contato.domain.service.ValidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 21:02
 */

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    @Qualifier("novaPessoa")
    private ValidaService validaService;

    @Override
    public List<Pessoa> listarTodasPessoas() {
        return repository.findAll();
    }

    @Override
    public Pessoa criarPessoa(Pessoa pessoa) {
        validaService.validar(pessoa);
        return repository.save(pessoa);
    }
}
