package com.erik5594.contato.domain.service.impl;

import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.entity.Pessoa;
import com.erik5594.contato.domain.exception.pessoa.*;
import com.erik5594.contato.domain.repository.PessoaRepository;
import com.erik5594.contato.domain.service.ValidaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 14:08
 */

@Qualifier("novaPessoa")
@Service
public class ValidaNovaPessoaServiceImpl implements ValidaService<Pessoa> {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired @Qualifier("novoContato")
    private ValidaService validaService;

    @Override
    public void validar(Pessoa pessoa) {

        this.validarCamposObrigatorios(pessoa);
        this.validarCpf(pessoa.getCpf());
        this.validarDataNascimento(pessoa.getDataNascimento());
        this.validarContatos(pessoa.getContatos());

    }

    private void validarCamposObrigatorios(Pessoa pessoa){
        if(pessoa == null)
            throw new PessoaObrigatorioException();

        if(StringUtils.isBlank(pessoa.getNome()))
            throw new NomePessoaObrigatorioException();

        if(StringUtils.isBlank(pessoa.getCpf()))
            throw new CpfPessoaObrigatorioException();

        if(pessoa.getDataNascimento() == null)
            throw new DataNascimentoPessoaObrigatorioException();

        if(pessoa.getContatos().isEmpty())
            throw new ContatoPessoaObrigatorioException();
    }

    private void validarCpf(String cpf){
        if(cpf.length() != 11)
            throw new CpfPessoaInvalidoException();

        if(pessoaRepository.findPessoaByCpfEquals(cpf) != null)
            throw new CpfPessoaJaExisteException();
    }

    private void validarDataNascimento(Date dataNascimento){
        if(dataNascimento.after(new Date()))
            throw new DataNascimentoPessoaInvalidoException();
    }

    private void validarContatos(List<Contato> contatos){
        for (Contato contato : contatos) {
            validaService.validar(contato);
        }
    }
}
