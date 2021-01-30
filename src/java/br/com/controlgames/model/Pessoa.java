
package br.com.controlgames.model;

abstract public class Pessoa {
    private int idPessoa;
    private String cpf;
    private String nome;

    public Pessoa(int idPessoa, String cpf, String nome) {
        this.idPessoa = idPessoa;
        this.cpf = cpf;
        this.nome = nome;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
