package com.erik5594.contato.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 19:48
 */
@ApiModel("Pessoa")
public class PessoaDTO {

    @ApiModelProperty(example = "1", accessMode= ApiModelProperty.AccessMode.READ_ONLY)
    public Long id;
    @ApiModelProperty(required = true)
    public String nome;
    @ApiModelProperty(required = true)
    public String cpf;
    @ApiModelProperty(example = "01/12/1999", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
    public Date dataNascimento;

    @ApiModelProperty(required = true)
    public List<ContatoDTO> contatos;

}
