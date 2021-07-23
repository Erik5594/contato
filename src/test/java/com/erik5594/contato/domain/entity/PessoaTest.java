package com.erik5594.contato.domain.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 16:28
 */
public class PessoaTest {

    private Pessoa pessoa;

    @Before
    public void before(){
        pessoa = new Pessoa();
    }

    @Test
    public void testeSetCpfComMascara(){
        //cenario
        //implementado no metodo before

        //acao
        pessoa.setCpf("123.456.789-01");

        //verificao
        Assert.assertEquals(11, pessoa.getCpf().length());
        Assert.assertEquals("12345678901", pessoa.getCpf());
    }

    @Test
    public void testeSetCpfSemMascara(){
        //cenario
        //implementado no metodo before

        //acao
        pessoa.setCpf("12345678901");

        //verificao
        Assert.assertEquals(11, pessoa.getCpf().length());
        Assert.assertEquals("12345678901", pessoa.getCpf());
    }

    @Test
    public void testeSetCpfNull(){
        //cenario
        //implementado no metodo before

        //acao
        pessoa.setCpf(null);

        //verificao
        Assert.assertNull(pessoa.getCpf());
    }

}
