package com.erik5594.contato.api.controller;

import com.erik5594.contato.api.assembler.PessoaDtoAssembler;
import com.erik5594.contato.api.dto.PessoaDTO;
import com.erik5594.contato.domain.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


/**
 * @author erik_
 * Data Criacao: 21/07/2021 - 19:44
 */

@Api(tags = "Pessoas")
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaDtoAssembler assembler;

    @ApiOperation("Listar todas pessoas cadastradas.")
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> getPessoas(){
        return ResponseEntity.ok(assembler.collectionEntityToDto(pessoaService.listarTodasPessoas()));
    }

    @ApiOperation("Cadastrar uma pessoa.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> cadastrarPessoa(@ApiParam(name = "Pessoa", required = true, value = "Representação de uma pessoa.") @RequestBody PessoaDTO pessoaDTO){
        pessoaDTO = assembler.entityToDto(pessoaService.criarPessoa(assembler.dtoToEntity(pessoaDTO)));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pessoaDTO.id).toUri();

        return ResponseEntity.created(uri).build();
    }
}
