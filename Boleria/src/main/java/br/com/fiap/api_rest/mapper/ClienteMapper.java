package br.com.fiap.api_rest.mapper;

import br.com.fiap.api_rest.dto.ClienteRequest;
import br.com.fiap.api_rest.dto.ClienteResponse;
import br.com.fiap.api_rest.model.Cliente;

public class ClienteMapper {

    public Cliente requestToCliente(ClienteRequest clienteRequest) {
        return new Cliente(
                null,
                clienteRequest.nome(),
                clienteRequest.idade(),
                clienteRequest.cpf(),
                clienteRequest.contato()
        );
    }

    public ClienteResponse clienteToResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome()
        );
    }
}
