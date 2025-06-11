package com.course.ais.io_algafood_api.domain.model;

public enum StatusPedido {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}


//public enum StatusPedido {
//    CRIADO("Criado"),
//    CONFIRMADO("Confirmado", CRIADO),
//    ENTREGUE("Entregue", CONFIRMADO),
//    CANCELADO("Cancelado", CRIADO);
//
//    private String descricao;
//    private List<StatusPedido> statusAnteriores;
//
//    StatusPedido(String descricao, StatusPedido... statusAnteriores) {
//        this.descricao = descricao;
//        this.statusAnteriores = Arrays.asList( statusAnteriores);
//    }
//
//    public String getDescricao() {
//        return descricao;
//    }
//
//    public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
//        return !novoStatus.statusAnteriores.contains(this);
//    }
//}