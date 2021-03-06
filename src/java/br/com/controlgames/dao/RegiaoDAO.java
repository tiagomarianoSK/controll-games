package br.com.controlgames.dao;

import br.com.controlgames.model.Regiao;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegiaoDAO implements GenericDAO {

    private Connection conexao;

    public RegiaoDAO() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Regiao oRegiao = (Regiao) objeto;
        Boolean retorno = false;

        if (oRegiao.getIdregiao() == 0) {
            retorno = this.inserir(oRegiao);
        } else {
            retorno = this.alterar(oRegiao);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Regiao oRegiao = (Regiao) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into regiao (descricao, situacao) values (?, ?)";

        try {

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oRegiao.getDescricao());
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
        Regiao oRegiao = (Regiao) objeto;
        PreparedStatement stmt = null;
        String sql = "update regiao set descricao=? where idregiao=?";

        try {
            
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oRegiao.getDescricao());
            stmt.setInt(2, oRegiao.getIdregiao());
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar regiao! Erro: " + ex.getMessage());
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
        Regiao oRegiao =  (Regiao) objeto;
        PreparedStatement stmt = null;

        String situacao = "A";
        if (oRegiao.getSituacao().equals(situacao)) {
            situacao = "I";
        } else {
            situacao = "A";
        }
        String sql = "update regiao set situacao=? where idregiao=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oRegiao.getIdregiao());
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
        Regiao oRegiao = null;
        String sql = "SELECT idregiao, descricao, situacao FROM regiao WHERE idregiao = ? ";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idRegiao);
            rs = stmt.executeQuery();

            while (rs.next()) {
                oRegiao = new Regiao(rs.getInt("idregiao"),
                        rs.getString("descricao"),
                        rs.getString("situacao"));
            }
            return oRegiao;
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

        String sql = "SELECT * FROM regiao";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Regiao oRegiao = new Regiao(rs.getInt("idregiao"),
                        rs.getString("descricao"),
                        rs.getString("situacao"));
                
                resultado.add(oRegiao);
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
