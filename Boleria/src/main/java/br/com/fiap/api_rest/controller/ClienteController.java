package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.ClienteRequest;
import br.com.fiap.api_rest.dto.ClienteResponse;
import br.com.fiap.api_rest.mapper.ClienteMapper;
import br.com.fiap.api_rest.model.Cliente;
import br.com.fiap.api_rest.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Tag(name = "api-clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    private final ClienteMapper clienteMapper = new ClienteMapper();

    @Operation(summary = "Registra um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente registrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<ClienteResponse> createCliente(@Valid @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.requestToCliente(clienteRequest);
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os clientes")
    @GetMapping
    public ResponseEntity<Page<ClienteResponse>> readClientes(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("nome").ascending());
        return new ResponseEntity<>(clienteService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Retorna um cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> readCliente(@PathVariable Long id) {
        ClienteResponse clienteResponse = clienteService.findById(id);
        return clienteResponse != null ?
                new ResponseEntity<>(clienteResponse, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Atualiza um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteResponse.class))),
            @ApiResponse(responseCode = "400", description = "Nenhum cliente encontrado",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> updateCliente(@PathVariable Long id, @Valid @RequestBody ClienteRequest clienteRequest) {
        ClienteResponse clienteExistente = clienteService.findById(id);
        if (clienteExistente == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Cliente clienteAtualizado = clienteMapper.requestToCliente(clienteRequest);
        clienteAtualizado.setId(id);
        return new ResponseEntity<>(clienteService.save(clienteAtualizado), HttpStatus.OK);
    }

    @Operation(summary = "Exclui um cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente excluído",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Nenhum cliente encontrado",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        boolean deletado = clienteService.deleteById(id);
        return deletado ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
