/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.model;

/**
 *
 * @author jeffersonpasserini
 */
public class Modelo {
    
    private int idModelo;
    private String descricao;
    private Marca marca;
    private String situacao;

    public Modelo() {
    }

    public Modelo(int idModelo, String descricao, Marca marca) {
        this.idModelo = idModelo;
        this.descricao = descricao;
        this.marca = marca;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
    
}

