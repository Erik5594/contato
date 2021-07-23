package com.erik5594.contato.domain.repository;

import com.erik5594.contato.domain.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 21:43
 */
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Contato findByNomeEqualsAndPessoa_IdEquals(String nome, Long idPessoa);
    Contato findByTelefoneEqualsAndPessoa_IdEquals(String telefone, Long idPessoa);
    Contato findByEmailEqualsAndPessoa_IdEquals(String email, Long idPessoa);
    Contato findByTelefoneEquals(String telefone);
    Contato findByEmailEquals(String email);
}
