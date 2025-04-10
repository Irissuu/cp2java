package br.com.fiap.api_rest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 200)
    private String nome;

    @NotNull(message = "A idade é obrigatória")
    private int idade;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF fora do formato correto")
    private String cpf;

    @NotNull(message = "O contato é obrigatório")
    private Long contato;

    public Cliente(Long id, String nome, int idade, String cpf, Long contato) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.contato = contato;
    }

    public Cliente() {

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getContato() {
        return contato;
    }

    public void setContato(Long contato) {
        this.contato = contato;
    }
}
