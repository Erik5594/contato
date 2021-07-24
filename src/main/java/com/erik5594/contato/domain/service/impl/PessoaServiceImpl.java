package com.erik5594.contato.domain.service.impl;

import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.entity.Pessoa;
import com.erik5594.contato.domain.exception.pessoa.PessoaNotFoundException;
import com.erik5594.contato.domain.repository.ContatoRepository;
import com.erik5594.contato.domain.repository.PessoaRepository;
import com.erik5594.contato.domain.service.PessoaService;
import com.erik5594.contato.domain.service.ValidaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 21:02
 */

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    @Qualifier("novaPessoa")
    private ValidaService validaNovaPessoa;

    @Autowired
    @Qualifier("atualizacaoPessoa")
    private ValidaService validaAtualizacaoPessoa;

    @Override
    public Pessoa buscar(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if(!pessoa.isPresent())
            throw new PessoaNotFoundException();
        return pessoa.get();
    }

    @Override
    public Page<Pessoa> listarTodasPessoas(Pessoa pessoa, Pageable pageable) {
        if (pessoa != null && (StringUtils.isNotBlank(pessoa.getNome()) || StringUtils.isNotBlank(pessoa.getCpf()) || pessoa.getDataNascimento() != null)) {
            ExampleMatcher example = ExampleMatcher.matchingAny()
                    .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                    .withMatcher("cpf", ExampleMatcher.GenericPropertyMatchers.contains())
                    .withMatcher("dataNascimento", ExampleMatcher.GenericPropertyMatchers.exact());
            Example<Pessoa> pessoaExemplo = Example.of(pessoa, example);
            return pessoaRepository.findAll(pessoaExemplo, pageable);
        }else{
            return pessoaRepository.findAll(pageable);
        }

    }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        validaNovaPessoa.validar(pessoa);
        return pessoaRepository.save(pessoa);
    }

    @Override
    public void atualizar(Pessoa pessoa) {
        deletarTodosContatos(pessoa);
        validaAtualizacaoPessoa.validar(pessoa);
        pessoaRepository.save(pessoa);
    }

    @Override
    public void deletar(Long id) {
        Pessoa pessoa = buscar(id);
        pessoaRepository.delete(pessoa);
    }

    private void deletarTodosContatos(Pessoa pessoa){
        List<Contato> contatos = contatoRepository.findAllByPessoaEquals(pessoa);
        contatos.stream().forEach(contatoRepository::delete);
    }

}
