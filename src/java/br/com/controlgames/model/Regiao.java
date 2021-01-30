/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.model;

/**
 *
 * @author Anonymous
 */
public class Regiao {
    
    private int idregiao;
    private String descricao;
    private String situacao;

    public Regiao(int idregiao, String descricao, String situacao) {
        this.idregiao = idregiao;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public int getIdregiao() {
        return idregiao;
    }

    public void setIdregiao(int idregiao) {
        this.idregiao = idregiao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
}
