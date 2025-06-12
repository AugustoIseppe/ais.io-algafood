package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.api.assembler.CozinhaInputDisassembler;
import com.course.ais.io_algafood_api.api.assembler.CozinhaModelAssembler;
import com.course.ais.io_algafood_api.api.model.dto.input.CozinhaInput;
import com.course.ais.io_algafood_api.api.model.dto.output.CozinhaModel;
import com.course.ais.io_algafood_api.domain.model.Cozinha;
import com.course.ais.io_algafood_api.domain.repository.CozinhaRepository;
import com.course.ais.io_algafood_api.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;


    @Autowired
    private CozinhaModelAssembler cozinhaModelAssembler;

    @Autowired
    private CozinhaInputDisassembler cozinhaInputDisassembler;

    @GetMapping
    public Page<CozinhaModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);

        List<CozinhaModel> cozinhaModels = cozinhaModelAssembler.toCollectionModel(cozinhasPage.getContent());

        return new PageImpl<>(cozinhaModels, pageable, cozinhasPage.getTotalElements());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        cozinha = cadastroCozinhaService.salvar(cozinha);

        return cozinhaModelAssembler.toModel(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaModel atualizar(@PathVariable Long cozinhaId,
                                  @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
        cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);

        return cozinhaModelAssembler.toModel(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)    public void remover(@PathVariable Long cozinhaId) {
        cadastroCozinhaService.excluir(cozinhaId);
    }
}
