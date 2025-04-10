package br.com.fiap.api_rest.dto;

import jakarta.validation.constraints.*;

public class BoloRequest {

    @NotBlank(message = "O nome do bolo não pode ser nulo ou vazio")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O sabor do bolo não pode ser nulo ou vazio")
    private String sabor;

    private String recheio;

    private String calda;

    @NotNull(message = "O valor do bolo é obrigatório")
    private Double valor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getRecheio() {
        return recheio;
    }

    public void setRecheio(String recheio) {
        this.recheio = recheio;
    }

    public String getCalda() {
        return calda;
    }

    public void setCalda(String calda) {
        this.calda = calda;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
