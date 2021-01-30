
package br.com.controlgames.dao;

import br.com.controlgames.model.Usuario;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements GenericDAO{
    
    private Connection conexao;
    
    public UsuarioDAO() throws Exception{
        try{
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        }catch(Exception ex){
            throw new Exception("Problemas ao conectar no BD! Erro: "+ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Usuario oUsuario = (Usuario) objeto;
        Boolean retorno = false;
        
        if (oUsuario.getIdUsuario()==0) {
            int idUsuario = this.verificarCpf(oUsuario.getCpf());
            if (idUsuario==0) {
                retorno = this.inserir(oUsuario);
            }else{
                retorno = this.alterar(oUsuario);
            }
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Usuario oUsuario = (Usuario) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into usuario (idusuario, login, senha, situacao) values (?, ?, ?, ?)";
        
        try{
            PessoaDAO oPessoaDAO = new PessoaDAO();
            int idPessoa = oPessoaDAO.cadastrar(oUsuario);
            
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            stmt.setString(2, oUsuario.getLogin());
            stmt.setString(3, oUsuario.getSenha());
            stmt.setString(4, "A");
            stmt.execute();
            
            return true;
        }catch(Exception ex){
            System.out.println("Problemas ao incluir usuário! Erro "+ex.getMessage());
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
        Usuario oUsuario = (Usuario) objeto;
        PreparedStatement stmt = null;
        String sql = "update usuario set login=?, senha=? where idusuario=?";
        
        try{
            PessoaDAO oPessoaDAO = new PessoaDAO();
            oPessoaDAO.cadastrar(oUsuario);
            
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oUsuario.getLogin());
            stmt.setString(2, oUsuario.getSenha());
            stmt.setInt(3, oUsuario.getIdUsuario());
            stmt.execute();
            
            return true;
        }catch(Exception ex){
            System.out.println("Problemas ao alterar usuário! Erro: "+ex.getMessage());
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
        Usuario oUsuario =(Usuario) objeto;
        PreparedStatement stmt = null;
        
        String situacao="A";
        if(oUsuario.getSituacao().equals(situacao)){
            situacao = "I";
        }else{
            situacao = "A";
        }
        String sql = "update usuario set situacao=? where idusuario=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oUsuario.getIdUsuario());
            stmt.execute();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao ativar/desativar usuário! Erro: "+ex.getMessage());
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
        int idUsuario = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario oUsuario = null;
        String sql = "Select * from usuario u, pessoa p where u.idusuario = p.idpessoa and u.idusuario=?";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                oUsuario = new Usuario(rs.getInt("idusuario"),
                                       rs.getString("login"),
                                       rs.getString("senha"),
                                       rs.getString("situacao"),
                                       rs.getInt("idpessoa"),
                                       rs.getString("cpf"),
                                       rs.getString("nome"));
            }
            return oUsuario;
        }catch(SQLException ex){
            System.out.println("Problemas ao carregar usuário! Erro "+ex.getMessage());
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
        
        String sql= "Select * from usuario u, pessoa p where u.idusuario = p.idpessoa order by idpessoa";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
            Usuario oUsuario = new Usuario(rs.getInt("idusuario"),
                                       rs.getString("login"),
                                       rs.getString("senha"),
                                       rs.getString("situacao"),
                                       rs.getInt("idpessoa"),
                                       rs.getString("cpf"),
                                       rs.getString("nome"));
            resultado.add(oUsuario);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar Usuário! Erro "+ex.getMessage());
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
        int idUsuario = 0;
        String sql = "Select * from usuario u, pessoa p, where u.idusuario = p.idpessoa and p.cpf=?;";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                idUsuario = rs.getInt("idusuario");
            }
            return idUsuario;
        }catch(SQLException ex){
            System.out.println("Problemas ai carregar pessoa! Erro: "+ex.getMessage());
            return idUsuario;
        }
    }
    
    public Usuario logar(String login, String senha){
      PreparedStatement stmt = null;
      ResultSet rs = null;
      Usuario oUsuario = null;
      String sql = "Select * from usuario u, pessoa p where u.idusuario = p.idpessoa and u.login=? and u.senha=?"
              + "and situacao = 'A'";
      
      try{
          stmt=conexao.prepareStatement(sql);
          stmt.setString(1,login);
          stmt.setString(2, senha);
          rs=stmt.executeQuery();
          
          while (rs.next()){
              oUsuario = new Usuario(rs.getInt("idusuario"),
                                      rs.getString("login"),
                                      rs.getString("senha"),
                                      rs.getString("situacao"),
                                      rs.getInt("idpessoa"),
                                      rs.getString("cpf"),
                                      rs.getString("nome"));
              
          }
          return oUsuario;
      }catch (SQLException ex){
          System.out.println("Problemas ao carregar usuario!Erro"+ex.getMessage());
          return null;
      }finally{
          try{
              ConnectionFactory.closeConnection(conexao, stmt,rs);
              
          }catch(Exception ex){
          System.out.println("Problemas ao fechar os parametros de conexao!Erro:"+ex.getMessage());
      }
      }
}
}
