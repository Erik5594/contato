package com.erik5594.contato.domain.service;

/**
 * @author erik_
 * Data Criacao: 22/07/2021 - 14:07
 */
@FunctionalInterface
public interface ValidaService<T> {

    void validar(T object);

}
