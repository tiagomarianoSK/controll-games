
package br.com.controlgames.dao;


import br.com.controlgames.model.Vendedor;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO implements GenericDAO {

    private Connection conexao;

    public VendedorDAO() throws Exception {

        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Vendedor oVendedor = (Vendedor) objeto;
        Boolean retorno = false;

        if (oVendedor.getIdVendedor() == 0) {
            int idVendedor = this.verificarCpf(oVendedor.getCpf());
            if (idVendedor == 0) {
                retorno = this.inserir(oVendedor);
            } else {
                retorno = this.alterar(oVendedor);
            }
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Vendedor oVendedor = (Vendedor) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into vendedor (idvendedor, url, observacao, situacao) values (?, ?, ?,?)";

        try {
            PessoaDAO oPessoaDAO = new PessoaDAO();
            int idPessoa = oPessoaDAO.cadastrar(oVendedor);

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            stmt.setString(2, oVendedor.getUrl());
            stmt.setString(3, oVendedor.getObservacao());
            stmt.setString(4, "A");
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao incluir vendedor! Erro " + ex.getMessage());
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro: " + ex.getMessage());
            }
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Vendedor oVendedor = (Vendedor) objeto;
        PreparedStatement stmt = null;
        String sql = "update vendedor set url=?, observacao=? where idvendedor=?";

        try {
            PessoaDAO oPessoaDAO = new PessoaDAO();
            oPessoaDAO.cadastrar(oVendedor);

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oVendedor.getUrl());
            stmt.setString(2, oVendedor.getObservacao());
            stmt.setInt(3, oVendedor.getIdVendedor());
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar vendedor! Erro: " + ex.getMessage());
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro " + ex.getMessage());
            }
        }
    }

    @Override
    public Boolean excluir(Object objeto) {
        Vendedor oVendedor = (Vendedor) objeto;
        PreparedStatement stmt = null;

        String situacao = "A";
        if (oVendedor.getSituacao().equals(situacao)) {
            situacao = "I";
        } else {
            situacao = "A";
        }
        String sql = "update vendedor set situacao=? where idvendedor=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oVendedor.getIdVendedor());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao ativar/desativar vendedor! Erro: " + ex.getMessage());
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parâmetros de conexão! Erro: " + ex.getMessage());
            }
        }
    }

    @Override
    public Object carregar(int numero) {
        int idVendedor = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vendedor oVendedor = null;
        String sql = "Select * from vendedor v, pessoa p where v.idvendedor = p.idpessoa and v.idvendedor=?";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idVendedor);
            rs=stmt.executeQuery();
            
            while(rs.next()){
                oVendedor = new Vendedor(rs.getInt("idvendedor"),
                                       rs.getString("url"),
                                       rs.getString("observacao"),
                                       rs.getString("situacao"),
                                       rs.getInt("idpessoa"),
                                       rs.getString("cpf"),
                                       rs.getString("nome"));
            }
            return oVendedor;
        }catch(SQLException ex){
            System.out.println("Problemas ao carregar vendedor! Erro "+ex.getMessage());
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
        
        String sql= "Select * from vendedor v, pessoa p where v.idvendedor = p.idpessoa order by idpessoa";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
            Vendedor oVendedor = new Vendedor(rs.getInt("idvendedor"),
                                       rs.getString("url"),
                                       rs.getString("observacao"),
                                       rs.getString("situacao"),
                                       rs.getInt("idpessoa"),
                                       rs.getString("cpf"),
                                       rs.getString("nome"));
            resultado.add(oVendedor);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar Vendedor! Erro "+ex.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar parâmetros de conexão! Erro: "+ex.getMessage());
            }
        }
        return resultado;
    }

    public int verificarCpf(String cpf) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idVendedor = 0;
        String sql = "Select * from vendedor v, pessoa p, where v.idvendedor = p.idpessoa and p.cpf=?;";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();

            while (rs.next()) {
                idVendedor = rs.getInt("idvendedor");
            }
            return idVendedor;
        } catch (SQLException ex) {
            System.out.println("Problemas ai carregar pessoa! Erro: " + ex.getMessage());
            return idVendedor;
        }
    }

}
