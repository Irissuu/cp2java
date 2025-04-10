package br.com.fiap.api_rest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O valor do pedido é obrigatório")
    private Double valor;

    @NotBlank(message = "O nome do bolo é obrigatório")
    private String bolo;

    @NotBlank(message = "A data de fabricação é obrigatória")
    private String fabricacao;

    @NotBlank(message = "A data de validade é obrigatória")
    private String validade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getBolo() {
        return bolo;
    }

    public void setBolo(String bolo) {
        this.bolo = bolo;
    }

    public String getFabricacao() {
        return fabricacao;
    }

    public void setFabricacao(String fabricacao) {
        this.fabricacao = fabricacao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }
}
