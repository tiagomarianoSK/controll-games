
package br.com.controlgames.dao;

import java.util.List;

public interface GenericDAO {
    public Boolean cadastrar(Object objeto);
    public Boolean inserir(Object objeto);
    public Boolean alterar(Object objeto);
    public Boolean excluir(Object objeto);
    public Object carregar(int numero);
    public List<Object>listar();
}
