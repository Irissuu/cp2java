package br.com.fiap.api_rest.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 200) String nome,

        @NotNull(message = "A idade é obrigatória") int idade,

        @NotBlank(message = "O CPF é obrigatório")
        @CPF(message = "CPF fora do formato correto") String cpf,

        @NotNull(message = "O contato é obrigatório") Long contato
) {
}
