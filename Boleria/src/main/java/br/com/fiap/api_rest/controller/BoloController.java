package br.com.fiap.api_rest.controller;

import br.com.fiap.api_rest.dto.BoloRequest;
import br.com.fiap.api_rest.dto.BoloResponse;
import br.com.fiap.api_rest.mapper.BoloMapper;
import br.com.fiap.api_rest.model.Bolo;
import br.com.fiap.api_rest.service.BoloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bolos")
@Tag(name = "api-bolos")
public class BoloController {

    @Autowired
    private BoloService boloService;
    private final BoloMapper boloMapper = new BoloMapper();

    @Operation(summary = "Cadastra um novo bolo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bolo cadatrasdo",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Bolo.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<BoloResponse> createBolo(@Valid @RequestBody BoloRequest boloRequest) {
        Bolo bolo = boloMapper.requestToBolo(boloRequest);
        return new ResponseEntity<>(boloService.save(bolo), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista todos os bolos")
    @GetMapping
    public ResponseEntity<Page<BoloResponse>> readBolos(@RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("nome").ascending());
        return new ResponseEntity<>(boloService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Retorna um bolo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bolo encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BoloResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum bolo encontrado",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BoloResponse> readBolo(@PathVariable Long id) {
        BoloResponse boloResponse = boloService.findById(id);
        return boloResponse != null ?
                new ResponseEntity<>(boloResponse, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Atualiza um bolo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bolo atualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = BoloResponse.class))),
            @ApiResponse(responseCode = "400", description = "Nenhum bolo encontrado",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<BoloResponse> updateBolo(@PathVariable Long id, @Valid @RequestBody BoloRequest boloRequest) {
        BoloResponse boloExistente = boloService.findById(id);
        if (boloExistente == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Bolo boloAtualizado = boloMapper.requestToBolo(boloRequest);
        boloAtualizado.setId(id);
        return new ResponseEntity<>(boloService.save(boloAtualizado), HttpStatus.OK);
    }

    @Operation(summary = "Exclui um bolo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bolo excluído",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "400", description = "Nenhum bolo encontrado",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBolo(@PathVariable Long id) {
        boolean deletado = boloService.deleteById(id);
        return deletado ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
