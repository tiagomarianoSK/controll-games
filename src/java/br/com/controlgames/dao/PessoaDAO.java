
package br.com.controlgames.dao;

import br.com.controlgames.model.Pessoa;
import com.google.gson.Gson;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private Connection conexao;
    
    public PessoaDAO() throws Exception{
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com Sucesso"); 
       } catch (Exception ex) {
            System.out.println("Problemas ao conectar no BD! Erro: "+ex.getMessage());
       }
    }
    
    public int cadastrar(Object objeto){
        Pessoa oPessoa = (Pessoa) objeto;
        int retorno = 0;
        
        if (oPessoa.getIdPessoa()==0){
            int idPessoa = this.carregarCpf(oPessoa.getCpf());
            if (idPessoa==0) {
                retorno = this.inserir(oPessoa);
            }else{
                retorno = idPessoa;
            }
        }else{
            retorno = this.alterar(oPessoa);
        }
        return retorno;
    }
    
    public int carregarCpf(String cpf){
        PreparedStatement stmt = null;
        ResultSet rs= null;
        int idPessoa = 0;
        String sql="select * from pessoa where cpf=?;";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs=stmt.executeQuery();
            
            while (rs.next()) {                
                idPessoa = rs.getInt("idpessoa");
            }
            return idPessoa;
        }catch(SQLException ex){
            System.out.println("Problemas ao Carregar Pessoa! Erro: "+ex.getMessage());
            return idPessoa;
        }
    }
    
    public int inserir(Object objeto){
        Pessoa oPessoa = (Pessoa) objeto;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer idPessoa=null;
        String sql = "insert into pessoa (cpf, nome) values (?, ?) returning idpessoa;";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPessoa.getCpf());
            stmt.setString(2, oPessoa.getNome());
            rs=stmt.executeQuery();
            while (rs.next()) {                
                idPessoa = rs.getInt("Idpessoa");
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao cadastrar Pessoa! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parâmetros de conexão! Erro: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idPessoa;
    }
    
    public int alterar(Object objeto){
        Pessoa oPessoa = (Pessoa) objeto;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer idPessoa = null;
        String sql="update pessoa set nome=? where idpessoa=? returning idpessoa;";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPessoa.getNome());
            stmt.setInt(2, oPessoa.getIdPessoa());
            rs=stmt.executeQuery();
            while(rs.next()){
                idPessoa = rs.getInt("idpessoa");
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao cadastrar Pessoa! Erro: "+ex.getMessage());
            ex.printStackTrace();
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro: "+ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idPessoa;
    }
    
    public String carregarJSON(int idPessoa){
        int id = idPessoa;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> resultado = new ArrayList<>();
        String sql = "Select * from pessoa p where p.idpessoa=?";
        
        try{
            stmt= conexao.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            rs=stmt.executeQuery();
            while (rs.next()){
                resultado.add(String.valueOf(rs.getInt("idpessoa")));
                resultado.add(rs.getString("cpf"));
                resultado.add(rs.getString("nome"));
            }
            String json = new Gson().toJson(resultado);
            return json;
        }catch(SQLException ex){
            System.out.println("Problemas ao carregar pessoa! Erro: "+ex.getMessage());
            ex.printStackTrace();
            return null;
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro: "+ex.getMessage());
            }
        }
    }
    
}
