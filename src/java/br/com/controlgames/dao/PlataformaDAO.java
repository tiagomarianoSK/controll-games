package br.com.controlgames.dao;

import br.com.controlgames.model.Plataforma;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlataformaDAO implements GenericDAO {

    private Connection conexao;

    public PlataformaDAO() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Plataforma oPlataforma = (Plataforma) objeto;
        Boolean retorno = false;

        if (oPlataforma.getIdPlataforma()== 0) {
            retorno = this.inserir(oPlataforma);
        } else {
            retorno = this.alterar(oPlataforma);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Plataforma oPlataforma = (Plataforma) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into plataforma (descricao, situacao) values (?, ?)";

        try {

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPlataforma.getDescricao());
            stmt.setString(2, "A");
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao incluir plataforma! Erro " + ex.getMessage());
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
        Plataforma oPlataforma = (Plataforma) objeto;
        PreparedStatement stmt = null;
        String sql = "update plataforma set descricao=? where idplataforma=?";

        try {
            
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oPlataforma.getDescricao());
            stmt.setInt(2, oPlataforma.getIdPlataforma());
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar plataforma! Erro: " + ex.getMessage());
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
        Plataforma oPlataforma =  (Plataforma) objeto;
        PreparedStatement stmt = null;

        String situacao = "A";
        if (oPlataforma.getSituacao().equals(situacao)) {
            situacao = "I";
        } else {
            situacao = "A";
        }
        String sql = "update plataforma set situacao=? where idplataforma=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oPlataforma.getIdPlataforma());
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
        Plataforma oPlataforma = null;
        String sql = "SELECT idplataforma, descricao, situacao FROM plataforma WHERE idplataforma = ? ";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idRegiao);
            rs = stmt.executeQuery();

            while (rs.next()) {
                oPlataforma = new Plataforma(rs.getInt("idplataforma"),
                        rs.getString("descricao"),
                        rs.getString("situacao"));
            }
            return oPlataforma;
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar plataforma! Erro " + ex.getMessage());
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

        String sql = "SELECT * FROM plataforma";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Plataforma oPlataforma = new Plataforma(rs.getInt("idplataforma"),
                        rs.getString("descricao"),
                        rs.getString("situacao"));
                
                resultado.add(oPlataforma);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar plataforma! Erro " + ex.getMessage());
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
