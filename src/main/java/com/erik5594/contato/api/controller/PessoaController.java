package com.erik5594.contato.api.controller;

import com.erik5594.contato.api.assembler.PessoaDtoAssembler;
import com.erik5594.contato.api.dto.PessoaDTO;
import com.erik5594.contato.domain.entity.Pessoa;
import com.erik5594.contato.domain.service.PessoaService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
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

    @ApiOperation("Consultar os dados de uma pessoa.")
    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> getPessoa(@PathVariable Long id){
        return ResponseEntity.ok(assembler.entityToDto(pessoaService.buscar(id)));
    }

    @ApiOperation("Listar todas pessoas cadastradas.")
    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> getPessoas(@ApiParam(value = "Nome das pessoas a serem filtradas (Inicia com ?)") @RequestParam(value = "nome", required = false) String nome,
                                                      @ApiParam(value = "CPF das pessoas a serem filtradas (Inicia com ?)") @RequestParam(value = "cpf", required = false) String cpf,
                                                      @ApiParam(value = "Data de Nascimento das pessoas a serem filtradas (Nasceram em ?)")
                                                          @RequestParam(value = "dataNascimento", required = false)
                                                          @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East") Date dataNascimento,
                                                      @PageableDefault Pageable pageable){
        Page<Pessoa> pessoasPage = pessoaService.listarTodasPessoas(new Pessoa(nome, cpf, dataNascimento), pageable);

        List<PessoaDTO> pessoasDTO = assembler.collectionEntityToDto(pessoasPage.getContent());

        Page<PessoaDTO> pessoasDTOPage = new PageImpl<>(pessoasDTO, pageable, pessoasPage.getTotalElements());
        return ResponseEntity.ok(pessoasDTOPage);
    }

    @ApiOperation("Cadastrar uma pessoa.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> cadastrarPessoa(@ApiParam(name = "Pessoa", required = true, value = "Representação de uma pessoa.") @RequestBody PessoaDTO pessoaDTO){
        pessoaDTO = assembler.entityToDto(pessoaService.salvar(assembler.dtoToEntity(pessoaDTO)));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pessoaDTO.id).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation("Remover o cadastro de uma pessoa")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> alterar(@PathVariable Long id,
            @ApiParam(name = "Pessoa", required = true, value = "Representação de uma pessoa.") @RequestBody PessoaDTO pessoaDTO){
        pessoaDTO.id = id;
        pessoaService.atualizar(assembler.dtoToEntity(pessoaDTO));
        return ResponseEntity.accepted().build();
    }


    @ApiOperation("Remover o cadastro de uma pessoa")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pessoaService.deletar(id);
        return ResponseEntity.accepted().build();
    }
}
