package br.com.fiap.api_rest.dto;

import org.springframework.hateoas.Link;

public record BoloResponseDTO(Long id, String infoBolo, Link link) {
}
