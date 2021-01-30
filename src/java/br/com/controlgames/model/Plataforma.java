/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.model;

/**
 *
 * @author JJ
 */
public class Plataforma {
    
    private int idPlataforma;
    private String descricao;
    private String situacao;

    public Plataforma(int idPlataforma, String descricao, String situacao) {
        this.idPlataforma = idPlataforma;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public int getIdPlataforma() {
        return idPlataforma;
    }

    public void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
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
