package com.erik5594.contato.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 21:03
 */
@ApiModel("Contato")
public class ContatoDTO {

    @JsonIgnore
    public Long id;
    @ApiModelProperty(required = true)
    public String nome;
    @ApiModelProperty(example = "99999999999 (DDD+NUMERO apenas numeros)", required = true)
    public String telefone;
    @ApiModelProperty(example = "meu_email@dominio.com", required = true)
    public String email;

}
