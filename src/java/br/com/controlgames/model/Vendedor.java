package br.com.controlgames.model;

public class Vendedor extends Pessoa {

    private int idVendedor;
    private String url;
    private String observacao;
    private String situacao;

    public Vendedor(int idVendedor, String url, String observacao, String situacao, int idPessoa, String cpf, String nome) {
        super(idPessoa, cpf, nome);
        this.idVendedor = idVendedor;
        this.url = url;
        this.observacao = observacao;
        this.situacao = situacao;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    

}
