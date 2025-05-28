package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.api.model.CozinhaXmlWrapper;
import com.course.ais.io_algafood_api.domain.model.Cozinha;
import com.course.ais.io_algafood_api.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhaXmlWrapper listarXML() {
        return new CozinhaXmlWrapper(cozinhaRepository.listar());
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header(HttpHeaders.WARNING, "Cozinha não encontrada")
                    .build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }


    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);

        if (cozinhaAtual != null) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
            cozinhaRepository.salvar(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtual);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
        try{
            Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
            if (cozinha != null) {
                cozinhaRepository.remover(cozinha);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .build();

        }
    }
}
