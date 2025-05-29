package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.domain.exceptions.EntidadeEmUsoException;
import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.model.Cidade;
import com.course.ais.io_algafood_api.domain.model.Estado;
import com.course.ais.io_algafood_api.domain.repository.CidadeRepository;
import com.course.ais.io_algafood_api.domain.repository.EstadoRepository;
import com.course.ais.io_algafood_api.domain.service.CadastroCidadeService;
import com.course.ais.io_algafood_api.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
        if (cidade.isPresent()) {
            return ResponseEntity.ok(cidade.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        return cadastroCidadeService.salvar(cidade);
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);

        if (cidadeAtual.isPresent()) {
            BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
            Cidade cidadeSalva = cadastroCidadeService.salvar(cidadeAtual.get());
            return ResponseEntity.ok(cidadeSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<?> remover(@PathVariable Long cidadeId) {
        try {
            cadastroCidadeService.excluir(cidadeId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
