package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.api.assembler.PedidoInputDisassembler;
import com.course.ais.io_algafood_api.api.assembler.PedidoModelAssembler;
import com.course.ais.io_algafood_api.api.assembler.PedidoResumoModelAssembler;
import com.course.ais.io_algafood_api.api.model.dto.input.PedidoInput;
import com.course.ais.io_algafood_api.api.model.dto.output.PedidoModel;
import com.course.ais.io_algafood_api.api.model.dto.output.PedidoResumoModel;
import com.course.ais.io_algafood_api.domain.exceptions.EntidadeNaoEncontradaException;
import com.course.ais.io_algafood_api.domain.exceptions.NegocioException;
import com.course.ais.io_algafood_api.domain.model.Pedido;
import com.course.ais.io_algafood_api.domain.model.Usuario;
import com.course.ais.io_algafood_api.domain.repository.PedidoRepository;
import com.course.ais.io_algafood_api.domain.repository.filter.PedidoFilter;
import com.course.ais.io_algafood_api.domain.service.EmissaoPedidoService;
import com.course.ais.io_algafood_api.infrastructure.spec.PedidoSpecs;
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
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmissaoPedidoService emissaoPedido;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;


    @GetMapping
    public Page<PedidoResumoModel> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<Pedido> pedidosPage = pedidoRepository.findAll(pageable);

        List<PedidoResumoModel> pedidosResumoModels = pedidoResumoModelAssembler.toCollectionModel(pedidosPage.getContent());

        return new PageImpl<>(pedidosResumoModels, pageable, pedidosPage.getTotalElements());
    }

//    @GetMapping
//    public List<PedidoResumoModel> pesquisar(PedidoFilter filtro) {
//        List<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro));
//
//        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
//    }

    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

        return pedidoModelAssembler.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
}

