package com.erik5594.contato.domain.service.impl;

import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.entity.Pessoa;
import com.erik5594.contato.domain.exception.contato.*;
import com.erik5594.contato.domain.exception.pessoa.ContatoPessoaObrigatorioException;
import com.erik5594.contato.domain.repository.ContatoRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 17:25
 */
public class ValidaNovoContatoServiceImplTest {

    private ValidaContatoServiceImpl novoContato;
    private Contato contato;
    private ContatoRepository contatoRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before(){
        novoContato = new ValidaContatoServiceImpl();

        contatoRepository = Mockito.mock(ContatoRepository.class);
        novoContato.setRepository(contatoRepository);

        contato = getContato();
    }

    @Test
    public void testeValidarContatoNull(){
        //cenario
        expectedException.expect(ContatoPessoaObrigatorioException.class);

        //acao
        novoContato.validar(null);
    }

    @Test
    public void testeValidarContato_NomeNull(){
        //cenario
        contato.setNome(null);
        expectedException.expect(NomeContatoObrigatorioException.class);

        //acao
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarContato_NomeVazio(){
        //cenario
        contato.setNome("");
        expectedException.expect(NomeContatoObrigatorioException.class);

        //acao
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarContato_NomeEmBranco(){
        //cenario
        contato.setNome("   ");
        expectedException.expect(NomeContatoObrigatorioException.class);

        //acao
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarContato_EmailNull(){
        //cenario
        contato.setEmail(null);
        expectedException.expect(EmailContatoObrigatorioException.class);

        //acao
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarContato_EmailVazio(){
        //cenario
        contato.setEmail("");
        expectedException.expect(EmailContatoObrigatorioException.class);

        //acao
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarContato_EmailEmBranco(){
        //cenario
        contato.setEmail("   ");
        expectedException.expect(EmailContatoObrigatorioException.class);

        //acao
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarContato_TelefoneNull(){
        //cenario
        contato.setTelefone(null);
        expectedException.expect(TelefoneContatoObrigatorioException.class);

        //acao
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarContato_TelefoneVazio(){
        //cenario
        contato.setTelefone("");
        expectedException.expect(TelefoneContatoObrigatorioException.class);

        //acao
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarContato_TelefoneEmBranco(){
        //cenario
        contato.setTelefone("      ");
        expectedException.expect(TelefoneContatoObrigatorioException.class);

        //acao
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarTelefoneMenorQue10(){
        //cenario
        contato.setTelefone("3280-7810");
        expectedException.expect(TelefoneContatoInvalidoException.class);

        //a????o
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarTelefoneMenorQue10Numeros(){
        //cenario
        contato.setTelefone("32esh80-781asfs0");
        expectedException.expect(TelefoneContatoInvalidoException.class);

        //a????o
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarTelefoneJaCadastratoParaOutro(){
        //cenario
        List<Contato> contatos = Arrays.asList(contato);
        Mockito.when(contatoRepository.findByTelefoneEquals(contato.getTelefone())).thenReturn(contatos);

        expectedException.expect(TelefoneContatoJaExisteException.class);

        //a????o
        novoContato.validar(contato);
    }

    @Test
    public void testeValidaremailSemArroba(){
        //cenario
        contato.setEmail("testeteste.com");

        Mockito.when(contatoRepository.findByTelefoneEquals(contato.getTelefone())).thenReturn(null);

        expectedException.expect(EmailContatoInvalidoException.class);

        //a????o
        novoContato.validar(contato);
    }

    @Test
    public void testeValidarEmailJaCadastrado(){
        //cenario
        List<Contato> contatos = Arrays.asList(contato);
        Mockito.when(contatoRepository.findByTelefoneEquals(contato.getTelefone())).thenReturn(null);
        Mockito.when(contatoRepository.findByEmailEquals(contato.getEmail())).thenReturn(contatos);

        expectedException.expect(EmailContatoJaExisteException.class);

        //a????o
        novoContato.validar(contato);
    }

    private Contato getContato(){
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1l);

        Contato contato = new Contato();
        contato.setEmail("teste@teste.com");
        contato.setNome("Primario");
        contato.setTelefone("12345678901");
        contato.setPessoa(pessoa);

        return contato;
    }
}
