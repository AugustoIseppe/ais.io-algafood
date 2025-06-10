package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.api.assembler.PedidoModelAssembler;
import com.course.ais.io_algafood_api.api.assembler.PedidoResumoModelAssembler;
import com.course.ais.io_algafood_api.api.model.dto.output.PedidoModel;
import com.course.ais.io_algafood_api.api.model.dto.output.PedidoResumoModel;
import com.course.ais.io_algafood_api.domain.model.Pedido;
import com.course.ais.io_algafood_api.domain.repository.PedidoRepository;
import com.course.ais.io_algafood_api.domain.service.EmissaoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public List<PedidoResumoModel> listar() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();

        return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
    }

    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);

        return pedidoModelAssembler.toModel(pedido);
    }
}

