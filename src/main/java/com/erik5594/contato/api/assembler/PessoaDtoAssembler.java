package com.erik5594.contato.api.assembler;

import com.erik5594.contato.api.dto.ContatoDTO;
import com.erik5594.contato.api.dto.PessoaDTO;
import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.entity.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 20:53
 */

@Component
public class PessoaDtoAssembler {

    @Autowired
    private ContatoDtoAssembler contatoDtoAssembler;

    public List<PessoaDTO> collectionEntityToDto(List<Pessoa> pessoas){
        return pessoas.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public PessoaDTO entityToDto(Pessoa pessoa){
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.id = pessoa.getId();
        pessoaDTO.nome = pessoa.getNome();
        pessoaDTO.cpf = pessoa.getCpf();
        pessoaDTO.dataNascimento = pessoa.getDataNascimento();

        pessoaDTO.contatos = contatoEntityToDto(pessoa.getContatos());

        return pessoaDTO;
    }

    public Pessoa dtoToEntity(PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaDTO.id);
        pessoa.setNome(pessoaDTO.nome);
        pessoa.setCpf(pessoaDTO.cpf);
        pessoa.setDataNascimento(pessoaDTO.dataNascimento);

        pessoa.setContatos(contatoDtoToEntity(pessoaDTO.contatos == null ? new ArrayList<>():pessoaDTO.contatos));

        return pessoa;
    }

    private List<ContatoDTO> contatoEntityToDto(List<Contato> contatos){
        return contatos.stream()
                .map(contatoDtoAssembler::entityToDto)
                .collect(Collectors.toList());
    }

    private List<Contato> contatoDtoToEntity(List<ContatoDTO> contatosDTO){
        return contatosDTO.stream()
                .map(contatoDtoAssembler::dtoToEntity)
                .collect(Collectors.toList());
    }

}
