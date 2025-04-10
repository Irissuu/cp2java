package br.com.fiap.api_rest.service;

import br.com.fiap.api_rest.dto.ClienteResponse;
import br.com.fiap.api_rest.mapper.ClienteMapper;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper = new ClienteMapper();

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse save(Cliente cliente) {
        return clienteMapper.clienteToResponse(clienteRepository.save(cliente));
    }

    public Page<ClienteResponse> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable)
                .map(clienteMapper::clienteToResponse);
    }

    public ClienteResponse findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(clienteMapper::clienteToResponse).orElse(null);
    }

    public boolean deleteById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
            return true;
        }
        return false;
    }
}
