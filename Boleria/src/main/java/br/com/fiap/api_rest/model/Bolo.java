package br.com.fiap.api_rest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Bolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do bolo é obrigatório")
    private String nome;

    @NotBlank(message = "O sabor do bolo é obrigatório")
    private String sabor;

    private String recheio;

    private String calda;

    @NotNull(message = "O valor do bolo é obrigatório")
    @Min(value = 1, message = "O valor deve ser maior que zero")
    private double valor;

    public Bolo(Long id, String nome, String sabor, String recheio, String calda, double valor) {
        this.id = id;
        this.nome = nome;
        this.sabor = sabor;
        this.recheio = recheio;
        this.calda = calda;
        this.valor = valor;
    }

    public Bolo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
