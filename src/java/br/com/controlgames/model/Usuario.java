
package br.com.controlgames.model;

public class Usuario extends Pessoa{
    private int idUsuario;
    private String login;
    private String senha;
    private String situacao;

    public Usuario(int idUsuario, String login, String senha, String situacao, int idPessoa, String cpf, String nome) {
        super(idPessoa, cpf, nome);
        this.idUsuario = idUsuario;
        this.login = login;
        this.senha = senha;
        this.situacao = situacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
}
