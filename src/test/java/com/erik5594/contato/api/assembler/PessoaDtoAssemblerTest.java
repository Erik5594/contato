package com.erik5594.contato.api.assembler;

import com.erik5594.contato.api.dto.ContatoDTO;
import com.erik5594.contato.api.dto.PessoaDTO;
import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.entity.Pessoa;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author erik_
 * Data Criacao: 23/07/2021 - 09:46
 */
public class PessoaDtoAssemblerTest {

    private PessoaDtoAssembler pessoaDtoAssembler;

    @Before
    public void before(){
        pessoaDtoAssembler = new PessoaDtoAssembler();
    }

    @Test
    public void testeEntityToDto(){
        //cenario
        List<Contato> contatos = new ArrayList<>();

        Contato contato1 = new Contato();
        contato1.setId(1l);
        contato1.setNome("Primario");
        contato1.setTelefone("12345678901");
        contato1.setEmail("teste@teste.com");

        Contato contato2 = new Contato();
        contato2.setId(2l);
        contato2.setNome("Secundario");
        contato2.setTelefone("98765432101");
        contato2.setEmail("teste1@teste.com");

        contatos.add(contato1);
        contatos.add(contato2);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1l);
        pessoa.setNome("Pessoa1");
        pessoa.setCpf("12345678901");
        pessoa.setDataNascimento(new Date());
        pessoa.setContatos(contatos);

        //acao
        PessoaDTO pessoaDTO = pessoaDtoAssembler.entityToDto(pessoa);

        //verificacao
        Assert.assertEquals(pessoa.getId(), pessoaDTO.id);
        Assert.assertEquals(pessoa.getNome(), pessoaDTO.nome);
        Assert.assertEquals(pessoa.getCpf(), pessoaDTO.cpf);
        Assert.assertEquals(pessoa.getDataNascimento(), pessoaDTO.dataNascimento);
        Assert.assertEquals(pessoa.getContatos().size(), pessoaDTO.contatos.size());
    }

    @Test
    public void testeDtoToEntity(){
        //cenario
        List<ContatoDTO> contatosDTO = new ArrayList<>();
        ContatoDTO contatoDTO1 = new ContatoDTO();
        contatoDTO1.id = 1l;
        contatoDTO1.nome = "Primario";
        contatoDTO1.telefone = "(12) 3 4567-8901";
        contatoDTO1.email = "teste@teste.com";

        ContatoDTO contatoDTO2 = new ContatoDTO();
        contatoDTO2.id = 2l;
        contatoDTO2.nome = "Secundario";
        contatoDTO2.telefone = "(12) 6 4567-8901";
        contatoDTO2.email = "teste2@teste.com";

        contatosDTO.add(contatoDTO1);
        contatosDTO.add(contatoDTO2);

        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.id = 1l;
        pessoaDTO.nome = "Teste1";
        pessoaDTO.cpf = "999.999.999-99";
        pessoaDTO.dataNascimento = new Date();
        pessoaDTO.contatos = contatosDTO;

        //acao
        Pessoa pessoa = pessoaDtoAssembler.dtoToEntity(pessoaDTO);

        //verificacao
        Assert.assertEquals(pessoaDTO.id, pessoa.getId());
        Assert.assertEquals(pessoaDTO.nome.toUpperCase(), pessoa.getNome());
        Assert.assertEquals(pessoaDTO.cpf.replaceAll("\\D",""), pessoa.getCpf());
        Assert.assertEquals(pessoaDTO.dataNascimento, pessoa.getDataNascimento());
        Assert.assertEquals(pessoaDTO.contatos.size(), pessoa.getContatos().size());
    }
}
