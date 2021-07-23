package com.erik5594.contato.api.assembler;

import com.erik5594.contato.api.dto.ContatoDTO;
import com.erik5594.contato.api.dto.PessoaDTO;
import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.entity.Pessoa;
import org.springframework.stereotype.Component;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 20:53
 */

@Component
public class ContatoDtoAssembler {

    public ContatoDTO entityToDto(Contato contato){
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.id = contato.getId();
        contatoDTO.nome = contato.getNome();
        contatoDTO.telefone = contato.getTelefone();
        contatoDTO.email = contato.getEmail();
        return contatoDTO;
    }

    public Contato dtoToEntity(ContatoDTO contatoDTO){
        Contato contato = new Contato();
        contato.setId(contatoDTO.id);
        contato.setNome(contatoDTO.nome);
        contato.setTelefone(contatoDTO.telefone);
        contato.setEmail(contatoDTO.email);
        return contato;
    }

}
