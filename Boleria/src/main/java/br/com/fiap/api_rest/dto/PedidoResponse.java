package br.com.fiap.api_rest.dto;

public class PedidoResponse {

    private Long id;
    private Double valor;
    private String bolo;
    private String fabricacao;
    private String validade;

    public PedidoResponse(Long id, Double valor, String bolo, String fabricacao, String validade) {
        this.id = id;
        this.valor = valor;
        this.bolo = bolo;
        this.fabricacao = fabricacao;
        this.validade = validade;
    }

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
