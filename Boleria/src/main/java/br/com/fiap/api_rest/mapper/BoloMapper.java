package br.com.fiap.api_rest.mapper;

import br.com.fiap.api_rest.controller.BoloController;
import br.com.fiap.api_rest.dto.BoloRequest;
import br.com.fiap.api_rest.dto.BoloResponse;
import br.com.fiap.api_rest.dto.BoloResponseDTO;
import br.com.fiap.api_rest.model.Bolo;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class BoloMapper {

    public Bolo requestToBolo(BoloRequest boloRequest) {
        return new Bolo(
                null,
                boloRequest.getNome(),
                boloRequest.getSabor(),
                boloRequest.getRecheio(),
                boloRequest.getCalda(),
                boloRequest.getValor()
        );
    }

    public BoloResponse boloToResponse(Bolo bolo) {
        return new BoloResponse(
                bolo.getId(),
                bolo.getNome(),
                bolo.getSabor(),
                bolo.getRecheio(),
                bolo.getCalda(),
                bolo.getValor()
        );
    }

    public BoloResponseDTO boloToResponseDTO(Bolo bolo, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(BoloController.class).readBolo(bolo.getId())).withSelfRel();
        } else {
            link = linkTo(methodOn(BoloController.class).readBolos(0)).withRel("Lista de Bolos");
        }
        return new BoloResponseDTO(
                bolo.getId(),
                bolo.getNome() + " - " + bolo.getSabor(),
                link
        );
    }
}
