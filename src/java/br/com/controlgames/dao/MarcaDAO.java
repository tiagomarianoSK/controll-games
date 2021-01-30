/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.dao;

import br.com.controlgames.model.Marca;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeffersonpasserini
 */
public class MarcaDAO implements GenericDAO {
    
    private Connection conexao;
    
    public MarcaDAO() throws Exception{
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com Sucesso"); 
       } catch (Exception ex) {
            System.out.println("Problemas ao conectar no BD! Erro: "+ex.getMessage());
        }
    }


    @Override
    public Boolean cadastrar(Object objeto) {
        Marca oMarca = (Marca) objeto;
        Boolean retorno=false;
        if (oMarca.getIdMarca()== 0) {
            retorno = this.inserir(oMarca);
        }else{
            retorno = this.alterar(oMarca);
        }
        return retorno;
        
    }

    @Override
    public Boolean inserir(Object objeto) {
        Marca oMarca = (Marca) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into marca (descricao, situacao) values (?,?)";  
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oMarca.getDescricao());        
            stmt.setString(2, "A");
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Marca! Erro: "+ex.getMessage());
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
        Marca oMarca = (Marca) objeto;
        PreparedStatement stmt = null;
        String sql= "update marca set descricao=? where idmarca=?"; 
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oMarca.getDescricao());
            stmt.setInt(2, oMarca.getIdMarca());            
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Marca! Erro: "+ex.getMessage());
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
        Marca oMarca = (Marca) objeto;
        int idMarca = oMarca.getIdMarca();
        PreparedStatement stmt= null;
        
        String situacao="A";
        if(oMarca.getSituacao().equals(situacao)){
            situacao = "I";
        }else{
            situacao = "A";
        }
        String sql = "update marca set situacao=? where idmarca=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, idMarca); 
            stmt.execute();
            return true;         
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir Marca! Erro: "+ex.getMessage());
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
        int idMarca = numero;
        PreparedStatement stmt = null;
        ResultSet rs= null;
        Marca oMarca = null;
        String sql="select * from marca where idmarca=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMarca);
            rs=stmt.executeQuery();          
            while (rs.next()) {                
                oMarca = new Marca();
                oMarca.setIdMarca(rs.getInt("idmarca"));
                oMarca.setDescricao(rs.getString("descricao"));
                oMarca.setSituacao(rs.getString("situacao"));
            }
            return oMarca;
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Marca! Erro: "+ex.getMessage());
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
        String sql = "Select * from marca order by idmarca";               
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();           
            while (rs.next()) {                
                Marca oMarca = new Marca();
                oMarca.setIdMarca(rs.getInt("idmarca"));
                oMarca.setDescricao(rs.getString("descricao"));
                oMarca.setSituacao(rs.getString("situacao"));
                resultado.add(oMarca);
            }
        
        }catch (SQLException ex) {
            System.out.println("Problemas ao listar Marca! Erro: "+ex.getMessage());
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

