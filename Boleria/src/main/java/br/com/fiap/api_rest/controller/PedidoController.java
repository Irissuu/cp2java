package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.PedidoRequest;
import br.com.fiap.api_rest.dto.PedidoResponse;
import br.com.fiap.api_rest.mapper.PedidoMapper;
import br.com.fiap.api_rest.model.Pedido;
import br.com.fiap.api_rest.service.PedidoService;
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
@RequestMapping("/pedidos")
@Tag(name = "api-pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    private final PedidoMapper pedidoMapper = new PedidoMapper();

    @Operation(summary = "Registra um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido registrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<PedidoResponse> createPedido(@Valid @RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoMapper.requestToPedido(pedidoRequest);
        return new ResponseEntity<>(pedidoService.save(pedido), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os pedidos")
    @GetMapping
    public ResponseEntity<Page<PedidoResponse>> readPedidos(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("bolo").ascending());
        return new ResponseEntity<>(pedidoService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Retorna um pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> readPedido(@PathVariable Long id) {
        PedidoResponse pedidoResponse = pedidoService.findById(id);
        return pedidoResponse != null ?
                new ResponseEntity<>(pedidoResponse, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Atualiza um pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Nenhum pedido encontrado",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> updatePedido(@PathVariable Long id, @Valid @RequestBody PedidoRequest pedidoRequest) {
        Pedido pedidoExistente = pedidoService.findPedidoById(id);
        if (pedidoExistente == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Pedido pedidoAtualizado = pedidoMapper.requestToPedido(pedidoRequest);
        pedidoAtualizado.setId(pedidoExistente.getId());
        return new ResponseEntity<>(pedidoService.save(pedidoAtualizado), HttpStatus.OK);
    }

    @Operation(summary = "Exclui um pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido excluído",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Nenhum pedido encontrado",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        boolean deletado = pedidoService.deleteById(id);
        return deletado ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
