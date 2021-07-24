package com.erik5594.contato.domain.repository;

import com.erik5594.contato.domain.entity.Contato;
import com.erik5594.contato.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 21:43
 */
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByTelefoneEquals(String telefone);
    List<Contato> findByEmailEquals(String email);
    List<Contato> findAllByPessoaEquals(Pessoa pessoa);
}
