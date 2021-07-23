package com.erik5594.contato.domain.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 16:28
 */
public class ContatoTest {

    private  Contato contato;

    @Before
    public void before(){
        //cenario
        contato = new Contato();
    }

    @Test
    public void testeSetNomeMinusculo(){
        //acao
        contato.setNome("Teste01");

        //verificao
        Assert.assertEquals("TESTE01", contato.getNome());
    }

    @Test
    public void testeSetNomeMaiusculo(){
        //acao
        contato.setNome("TESTE01");

        //verificao
        Assert.assertEquals("TESTE01", contato.getNome());
    }

    @Test
    public void testeSetNomeNull(){
        //acao
        contato.setNome(null);

        //verificao
        Assert.assertNull(contato.getNome());
    }

    @Test
    public void testeSetTelefoneSomenteNumeros(){
        //acao
        contato.setTelefone("12345678901");

        //verificao
        Assert.assertEquals(11, contato.getTelefone().length());
        Assert.assertEquals("12345678901", contato.getTelefone());
    }

    @Test
    public void testeSetTelefoneNull(){
        //acao
        contato.setTelefone(null);

        //verificao
        Assert.assertNull(contato.getTelefone());
    }

    @Test
    public void testeSetTelefoneComMascara(){
        //acao
        contato.setTelefone("(12) 3 4567-8901");

        //verificao
        Assert.assertEquals(11, contato.getTelefone().length());
        Assert.assertEquals("12345678901", contato.getTelefone());
    }

}
