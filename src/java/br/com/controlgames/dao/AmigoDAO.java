
package br.com.controlgames.dao;

import br.com.controlgames.model.Amigo;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmigoDAO implements GenericDAO{
    
    private Connection conexao;
    
    public AmigoDAO() throws Exception{
        try{
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        }catch(Exception ex){
            throw new Exception("Problemas ao conectar no BD! Erro: "+ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Amigo oAmigo = (Amigo) objeto;
        Boolean retorno = false;
        
        if (oAmigo.getIdAmigo()==0) {
            int idAmigo = this.verificarCpf(oAmigo.getCpf());
            if (idAmigo==0) {
                retorno = this.inserir(oAmigo);
            }else{
                retorno = this.alterar(oAmigo);
            }
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Amigo oAmigo = (Amigo) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into amigo (idamigo, observacao,  situacao) values (?, ?, ?)";
        
        try{
            PessoaDAO oPessoaDAO = new PessoaDAO();
            int idPessoa = oPessoaDAO.cadastrar(oAmigo);
            
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            stmt.setString(2, oAmigo.getObservacao());
            stmt.setString(3, "A");
            stmt.execute();
            
            return true;
        }catch(Exception ex){
            System.out.println("Problemas ao incluir amigo! Erro "+ex.getMessage());
            return false;
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro: "+ex.getMessage());
            }
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Amigo oAmigo = (Amigo) objeto;
        PreparedStatement stmt = null;
        String sql = "update amigo set observacao=? where idamigo=?";
        
        try{
            PessoaDAO oPessoaDAO = new PessoaDAO();
            oPessoaDAO.cadastrar(oAmigo);
            
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oAmigo.getObservacao());
            stmt.setInt(2, oAmigo.getIdAmigo());
            stmt.execute();
            
            return true;
        }catch(Exception ex){
            System.out.println("Problemas ao alterar amigo! Erro: "+ex.getMessage());
            return false;
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro "+ex.getMessage());
            }
        }
    }

    @Override
    public Boolean excluir(Object objeto) {
        Amigo oAmigo =(Amigo) objeto;
        PreparedStatement stmt = null;
        
        String situacao="A";
        if(oAmigo.getSituacao().equals(situacao)){
            situacao = "I";
        }else{
            situacao = "A";
        }
        String sql = "update amigo set situacao=? where idamigo=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oAmigo.getIdAmigo());
            stmt.execute();
            return true;
        }catch (SQLException ex){
            System.out.println("Problemas ao ativar/desativar amigo! Erro: "+ex.getMessage());
            return false;
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parâmetros de conexão! Erro: "+ex.getMessage());
            }
        }
    }

    @Override
    public Object carregar(int numero) {
        int idAmigo = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Amigo oAmigo = null;
        String sql = "Select * from amigo a, pessoa p where a.idamigo = p.idpessoa and a.idamigo=?";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idAmigo);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                oAmigo = new Amigo(rs.getInt("idamigo"),
                                       rs.getString("observacao"),
                                       rs.getString("situacao"),
                                       rs.getInt("idpessoa"),
                                       rs.getString("cpf"),
                                       rs.getString("nome"));
            }
            return oAmigo;
        }catch(SQLException ex){
            System.out.println("Problemas ao carregar amigo! Erro "+ex.getMessage());
            return null;
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro "+ex.getMessage());
            }
        }   
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql= "Select * from amigo a, pessoa p where a.idamigo = p.idpessoa order by idpessoa";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
            Amigo oAmigo = new Amigo(rs.getInt("idamigo"),
                                       rs.getString("observacao"),
                                       rs.getString("situacao"),
                                       rs.getInt("idpessoa"),
                                       rs.getString("cpf"),
                                       rs.getString("nome"));
            resultado.add(oAmigo);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar amigo! Erro "+ex.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parâmetros d econexão! Erro: "+ex.getMessage());
            }
        }
        return resultado;
    }
    
    public int verificarCpf(String cpf){
        PreparedStatement stmt = null;
        ResultSet rs= null;
        int idAmigo = 0;
        String sql = "Select * from amigo a, pessoa p, where a.idamigo = p.idpessoa and p.cpf=?;";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                idAmigo = rs.getInt("idamigo");
            }
            return idAmigo;
        }catch(SQLException ex){
            System.out.println("Problemas ai carregar pessoa! Erro: "+ex.getMessage());
            return idAmigo;
        }
    }
    
}

