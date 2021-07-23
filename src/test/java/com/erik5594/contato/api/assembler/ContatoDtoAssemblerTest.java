package com.erik5594.contato.api.assembler;

import com.erik5594.contato.api.dto.ContatoDTO;
import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.entity.Pessoa;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 17:08
 */
public class ContatoDtoAssemblerTest {

    private ContatoDtoAssembler contatoDtoAssembler;

    @Before
    public void before(){
        contatoDtoAssembler = new ContatoDtoAssembler();
    }

    @Test
    public void testeEntityToDto(){
        //cenario
        Contato contato = new Contato();
        contato.setId(1l);
        contato.setNome("Primario");
        contato.setTelefone("12345678901");
        contato.setEmail("teste@teste.com");
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1l);
        pessoa.setNome("Pessoa1");
        pessoa.setCpf("12345678901");
        pessoa.setDataNascimento(new Date());
        contato.setPessoa(pessoa);

        //acao
        ContatoDTO contatoDTO = contatoDtoAssembler.entityToDto(contato);

        //verificacao
        Assert.assertEquals(contato.getId(), contatoDTO.id);
        Assert.assertEquals(contato.getNome(), contatoDTO.nome);
        Assert.assertEquals(contato.getTelefone(), contatoDTO.telefone);
        Assert.assertEquals(contato.getEmail(), contatoDTO.email);
    }

    @Test
    public void testeDtoToEntity(){
        //cenario
        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.id = 1l;
        contatoDTO.nome = "Primario";
        contatoDTO.telefone = "(12) 3 4567-8901";
        contatoDTO.email = "teste@teste.com";

        //acao
        Contato contato = contatoDtoAssembler.dtoToEntity(contatoDTO);

        //verificacao
        Assert.assertEquals(contatoDTO.id, contato.getId());
        Assert.assertEquals(contatoDTO.nome.toUpperCase(), contato.getNome());
        Assert.assertEquals(contatoDTO.telefone.replaceAll("\\D",""), contato.getTelefone());
        Assert.assertEquals(contatoDTO.email, contato.getEmail());
    }
}
