package br.com.fiap.api_rest.dto;

public class BoloResponse {

    private Long id;
    private String nome;
    private String sabor;
    private String recheio;
    private String calda;
    private Double valor;

    public BoloResponse(Long id, String nome, String sabor, String recheio, String calda, Double valor) {
        this.id = id;
        this.nome = nome;
        this.sabor = sabor;
        this.recheio = recheio;
        this.calda = calda;
        this.valor = valor;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
