package br.com.fiap.api_rest.mapper;

import br.com.fiap.api_rest.dto.PedidoRequest;
import br.com.fiap.api_rest.dto.PedidoResponse;
import br.com.fiap.api_rest.model.Pedido;

public class PedidoMapper {

    public Pedido requestToPedido(PedidoRequest request) {
        Pedido pedido = new Pedido();
        pedido.setValor(request.getValor());
        pedido.setBolo(request.getBolo());
        pedido.setFabricacao(request.getFabricacao());
        pedido.setValidade(request.getValidade());
        return pedido;
    }

    public PedidoResponse pedidoToResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getValor(),
                pedido.getBolo(),
                pedido.getFabricacao(),
                pedido.getValidade()
        );
    }
}
