
package br.com.controlgames.model;

public class Comprador extends Pessoa {
   
    private int idComprador;
    private String observacao;
    private String situacao;

    public Comprador(int idComprador, String observacao, String situacao, int idPessoa, String cpf, String nome) {
        super(idPessoa, cpf, nome);
        this.idComprador = idComprador;
        this.observacao = observacao;
        this.situacao = situacao;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
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
