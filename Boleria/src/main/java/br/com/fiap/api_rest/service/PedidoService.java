package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.PedidoResponse;
import br.com.fiap.api_rest.mapper.PedidoMapper;
import br.com.fiap.api_rest.model.Pedido;
import br.com.fiap.api_rest.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper = new PedidoMapper();

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoResponse save(Pedido pedido) {
        return pedidoMapper.pedidoToResponse(pedidoRepository.save(pedido));
    }

    public Page<PedidoResponse> findAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable)
                .map(pedidoMapper::pedidoToResponse);
    }

    public PedidoResponse findById(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(pedidoMapper::pedidoToResponse).orElse(null);
    }

    public Pedido findPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public boolean deleteById(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            pedidoRepository.delete(pedido.get());
            return true;
        }
        return false;
    }
}
