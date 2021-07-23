package com.erik5594.contato.domain.service.impl;

import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.entity.Pessoa;
import com.erik5594.contato.domain.exception.pessoa.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author erik_
 * Data Criacao: 23/07/2021 - 08:56
 */
public class ValidaNovoPessoaServiceImpTest {

    private ValidaNovaPessoaServiceImpl validaNova;
    private Pessoa pessoa;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before(){
        validaNova = new ValidaNovaPessoaServiceImpl();
        pessoa = getPessoa();
    }

    @Test
    public void testeValidarPessoaNull(){
        //cenario
        expectedException.expect(PessoaObrigatorioException.class);

        //acao
        validaNova.validar(null);
    }

    @Test
    public void testeValidarPessoa_NomeNull(){
        //cenario
        pessoa.setNome(null);

        expectedException.expect(NomePessoaObrigatorioException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_NomeVazio(){
        //cenario
        pessoa.setNome("");
        expectedException.expect(NomePessoaObrigatorioException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_NomeEmBranco(){
        //cenario
        pessoa.setNome("     ");
        expectedException.expect(NomePessoaObrigatorioException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_CpfNull(){
        //cenario
        pessoa.setCpf(null);
        expectedException.expect(CpfPessoaObrigatorioException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_CpfVazio(){
        //cenario
        pessoa.setCpf("");
        expectedException.expect(CpfPessoaObrigatorioException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_CpfEmBranco(){
        //cenario
        pessoa.setCpf("    ");
        expectedException.expect(CpfPessoaObrigatorioException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_DataNascimentoNull(){
        //cenario
        pessoa.setDataNascimento(null);
        expectedException.expect(DataNascimentoPessoaObrigatorioException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_SemContato(){
        //cenario
        pessoa.setContatos(null);
        expectedException.expect(ContatoPessoaObrigatorioException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_CpfInvalidoMenorQue11(){
        //cenario
        pessoa.setCpf("0326598");
        expectedException.expect(CpfPessoaInvalidoException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_CpfInvalido(){
        //cenario
        pessoa.setCpf("12345678901");
        expectedException.expect(CpfPessoaInvalidoException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_DataNascimentoFutura(){
        //cenario
        pessoa.setDataNascimento(getDataFutura());
        expectedException.expect(DataNascimentoPessoaInvalidoException.class);

        //acao
        validaNova.validar(pessoa);
    }

    @Test
    public void testeValidarPessoa_DataNascimentoAgora(){
        //cenario
        pessoa.setDataNascimento(new Date());
        expectedException.expect(DataNascimentoPessoaInvalidoException.class);

        //acao
        validaNova.validar(pessoa);
    }

    private Date getDataFutura(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 10);
        return calendar.getTime();
    }

    private Date getDataAntiga(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        return calendar.getTime();
    }

    private Pessoa getPessoa(){
        Contato contato = new Contato();
        contato.setNome("Primario");
        List<Contato> contatos = new ArrayList<>();
        contatos.add(contato);
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1l);
        pessoa.setNome("Teste 1");
        pessoa.setCpf("03554424188");
        pessoa.setDataNascimento(getDataAntiga());
        pessoa.setContatos(contatos);
        return pessoa;
    }
}
