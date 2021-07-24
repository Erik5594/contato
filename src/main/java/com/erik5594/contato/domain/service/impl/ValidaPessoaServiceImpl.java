package com.erik5594.contato.domain.service.impl;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
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

@Service
public abstract class ValidaPessoaServiceImpl implements ValidaService<Pessoa> {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired @Qualifier("validaContato")
    private ValidaService validaContatoService;

    void validarCamposObrigatorios(Pessoa pessoa){
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

    void validarCpf(String cpf){
        if(cpf.length() != 11)
            throw new CpfPessoaInvalidoException();
        try {
            new CPFValidator().assertValid(cpf);
        }catch (InvalidStateException e){
            throw new CpfPessoaInvalidoException();
        }
    }

    void validarJaExisteCpf(String cpf){
        List<Pessoa> pessoas = pessoaRepository.findPessoaByCpfEquals(cpf);
        if(pessoas != null && !pessoas.isEmpty())
            throw new CpfPessoaJaExisteException();
    }

    void validarDataNascimento(Date dataNascimento){
        if(dataNascimento.after(new Date()))
            throw new DataNascimentoPessoaInvalidoException();
    }

    void validarContato(Contato contato){
        validaContatoService.validar(contato);
    }

    protected void validarSeExiste(Long id) {
        pessoaRepository.findById(id);
    }

    public void setPessoaRepository(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
}
