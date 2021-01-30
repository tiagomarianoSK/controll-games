/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.dao;

import br.com.controlgames.model.Marca;
import br.com.controlgames.dao.MarcaDAO;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.util.List;
import br.com.controlgames.model.Modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author jeffersonpasserini
 */
public class ModeloDAO implements GenericDAO {
    
    private Connection conexao;
    
    public ModeloDAO() throws Exception{
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com Sucesso"); 
       } catch (Exception ex) {
            System.out.println("Problemas ao conectar no BD! Erro: "+ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto){
        Modelo oModelo = (Modelo) objeto;
        Boolean retorno=false;
        if (oModelo.getIdModelo()== 0) {
            retorno = this.inserir(oModelo);
        }else{
            retorno = this.alterar(oModelo);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Modelo oModelo = (Modelo) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into modelo (descricao, situacao, idmarca) values (?,?,?)";  
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oModelo.getDescricao());        
            stmt.setString(2, "A");
            stmt.setInt(3,oModelo.getMarca().getIdMarca());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Modelo! Erro: "+ex.getMessage());
            return false;
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexÃ£o! Erro: "+ex.getMessage());
            }
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Modelo oModelo = (Modelo) objeto;
        PreparedStatement stmt = null;
        String sql= "update modelo set descricao=?, idmarca=? where idmodelo=?"; 
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oModelo.getDescricao());
            stmt.setInt(2,oModelo.getMarca().getIdMarca());
            stmt.setInt(3, oModelo.getIdModelo());            
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Modelo! Erro: "+ex.getMessage());
            return false;
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexÃ£o! Erro: "+ex.getMessage());
            }
        }

    }

    @Override
    public Boolean excluir(Object objeto) {
        Modelo oModelo = (Modelo) objeto;
        int idModelo = oModelo.getIdModelo();
        PreparedStatement stmt= null;
        
        String situacao="A";
        if(oModelo.getSituacao().equals(situacao)){
            situacao = "I";
        }else{
            situacao = "A";
        }
        String sql = "update modelo set situacao=? where idmodelo=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, idModelo); 
            stmt.execute();
            return true;         
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir Modelo! Erro: "+ex.getMessage());
            return false;           
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexÃ£o! Erro: "+ex.getMessage());
            }
        }

    }

    @Override
    public Object carregar(int numero) {
        int idModelo = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Modelo oModelo = null;
        String sql="select * from modelo where idmodelo=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idModelo);
            rs=stmt.executeQuery();          
            while (rs.next()) {                
                oModelo = new Modelo();
                oModelo.setIdModelo(rs.getInt("idmodelo"));
                oModelo.setDescricao(rs.getString("descricao"));
                oModelo.setSituacao(rs.getString("situacao"));
                //busca a marca do modelo
                MarcaDAO oMarcaDAO = new MarcaDAO();
                Marca oMarca = (Marca) oMarcaDAO.carregar(rs.getInt("idmarca"));
                oModelo.setMarca(oMarca);
            }
            return oModelo;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Modelo! Erro: "+ex.getMessage());
            return false;   
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexÃ£o! Erro: "+ex.getMessage());
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from modelo order by idmodelo";               
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();           
            while (rs.next()) {                
                Modelo oModelo = new Modelo();
                oModelo.setIdModelo(rs.getInt("idmodelo"));
                oModelo.setDescricao(rs.getString("descricao"));
                oModelo.setSituacao(rs.getString("situacao"));
                //busca a marca do modelo
                MarcaDAO oMarcaDAO;
                try {
                    oMarcaDAO = new MarcaDAO();
                    Marca oMarca = (Marca) oMarcaDAO.carregar(rs.getInt("idmarca"));
                    oModelo.setMarca(oMarca);
                } catch (Exception ex) {
                    System.out.println("Problemas ao buscar Marca! Erro: "+ex.getMessage());
                }
                resultado.add(oModelo);
            }
        
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Modelo! Erro: "+ex.getMessage());
        }
        finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parametros de conexÃ£o! Erro: "+ex.getMessage());
            }
        }
        return resultado;
    }
    
}

