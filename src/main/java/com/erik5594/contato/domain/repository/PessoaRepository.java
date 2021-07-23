package com.erik5594.contato.domain.repository;

import com.erik5594.contato.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 21:43
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findPessoaByCpfEquals(String cpf);
}
