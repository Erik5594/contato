package com.erik5594.contato.domain.repository;

import com.erik5594.contato.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 21:43
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, QueryByExampleExecutor<Pessoa> {
    List<Pessoa> findPessoaByCpfEquals(String cpf);
}
