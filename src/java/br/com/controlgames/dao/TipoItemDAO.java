package br.com.controlgames.dao;

import br.com.controlgames.model.TipoItem;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoItemDAO implements GenericDAO {

    private Connection conexao;

    public TipoItemDAO() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        TipoItem oTipoItem = (TipoItem) objeto;
        Boolean retorno = false;

        if (oTipoItem.getIdTipoItem()== 0) {
            retorno = this.inserir(oTipoItem);
        } else {
            retorno = this.alterar(oTipoItem);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        TipoItem oTipoItem = (TipoItem) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into tipoitem (descricao, situacao) values (?, ?)";

        try {

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoItem.getDescricao());
            stmt.setString(2, "A");
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao incluir regiao! Erro " + ex.getMessage());
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
        TipoItem oTipoItem = (TipoItem) objeto;
        PreparedStatement stmt = null;
        String sql = "update tipoitem set descricao=? where idtipoitem=?";

        try {
            
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoItem.getDescricao());
            stmt.setInt(2, oTipoItem.getIdTipoItem());
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar TipoItem! Erro: " + ex.getMessage());
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
        TipoItem oTipoItem = (TipoItem) objeto;
        PreparedStatement stmt = null;

        String situacao = "A";
        if (oTipoItem.getSituacao().equals(situacao)) {
            situacao = "I";
        } else {
            situacao = "A";
        }
        String sql = "update tipoitem set situacao=? where idregiao=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oTipoItem.getIdTipoItem());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao ativar/desativar usuário! Erro: " + ex.getMessage());
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
        int idRegiao = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TipoItem oTipoItem = null;
        String sql = "SELECT idtipoitem, descricao, situacao FROM tipoitem WHERE idtipoitem = ? ";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idRegiao);
            rs = stmt.executeQuery();

            while (rs.next()) {
                oTipoItem = new TipoItem(rs.getInt("idtipoitem"),
                        rs.getString("descricao"),
                        rs.getString("situacao"));
            }
            return oTipoItem;
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar regiao! Erro " + ex.getMessage());
            return null;
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro " + ex.getMessage());
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM tipoitem ";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TipoItem oTipoItem = new TipoItem(rs.getInt("idtipoitem"),
                        rs.getString("descricao"),
                        rs.getString("situacao"));
                
                resultado.add(oTipoItem);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Usuário! Erro " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parâmetros d econexão! Erro: " + ex.getMessage());
            }
        }
        return resultado;
    }
    
}
