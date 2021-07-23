package com.erik5594.contato.domain.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 21:06
 */
@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(StringUtils.isNotBlank(nome))
            nome = nome.toUpperCase();

        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if(StringUtils.isNotBlank(telefone))
            telefone = telefone.replaceAll("\\D","").trim();
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
