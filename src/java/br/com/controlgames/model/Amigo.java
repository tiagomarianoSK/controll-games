
package br.com.controlgames.model;

public class Amigo extends Pessoa {
    private int idAmigo;
    private String observacao;
    private String situacao;

    public Amigo(int idAmigo, String observacao, String situacao, int idPessoa, String cpf, String nome) {
        super(idPessoa, cpf, nome);
        this.idAmigo = idAmigo;
        this.observacao = observacao;
        this.situacao = situacao;
    }

    public int getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(int idAmigo) {
        this.idAmigo = idAmigo;
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
