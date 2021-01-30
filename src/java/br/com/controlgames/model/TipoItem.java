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
public class TipoItem {
    
    private int idTipoItem;
    private String descricao;
    private String situacao;

    public TipoItem(int idTipoItem, String descricao, String situacao) {
        this.idTipoItem = idTipoItem;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public int getIdTipoItem() {
        return idTipoItem;
    }

    public void setIdTipoItem(int idTipoItem) {
        this.idTipoItem = idTipoItem;
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
