package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.api.assembler.PermissaoModelAssembler;
import com.course.ais.io_algafood_api.api.model.dto.output.PermissaoModel;
import com.course.ais.io_algafood_api.domain.model.Grupo;
import com.course.ais.io_algafood_api.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;

    @GetMapping
    public List<PermissaoModel> listar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

        return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes());
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupo.desassociarPermissao(grupoId, permissaoId);
    }

    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
        cadastroGrupo.associarPermissao(grupoId, permissaoId);
    }

}

