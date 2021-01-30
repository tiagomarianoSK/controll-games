package br.com.controlgames.dao;

import br.com.controlgames.model.Procedencia;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedenciaDAO implements GenericDAO {

    private Connection conexao;

    public ProcedenciaDAO() throws Exception {
        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Procedencia oProcedencia = (Procedencia) objeto;
        Boolean retorno = false;

        if (oProcedencia.getIdProcedencia() == 0) {
            retorno = this.inserir(oProcedencia);
        } else {
            retorno = this.alterar(oProcedencia);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Procedencia oProcedencia = (Procedencia) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into procedencia(descricao, situacao) values (?, ?)";

        try {

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProcedencia.getDescricao());
            stmt.setString(2, "A");
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao incluir procedencia! Erro " + ex.getMessage());
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
        Procedencia oProcedencia = (Procedencia) objeto;
        PreparedStatement stmt = null;
        String sql = "update procedencia set descricao=? where idprocedencia=?";

        try {
            
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProcedencia.getDescricao());
            stmt.setInt(2, oProcedencia.getIdProcedencia());
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar procedencia! Erro: " + ex.getMessage());
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
        Procedencia oProcedencia = (Procedencia) objeto;
        PreparedStatement stmt = null;

        String situacao = "A";
        if (oProcedencia.getSituacao().equals(situacao)) {
            situacao = "I";
        } else {
            situacao = "A";
        }
        String sql = "update procedencia set situacao=? where idprocedencia=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oProcedencia.getIdProcedencia());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao ativar/desativar procedencia! Erro: " + ex.getMessage());
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
        Procedencia oProcedencia = null;
        String sql = "SELECT idprocedencia, descricao, situacao FROM procedencia WHERE idprocedencia = ? ";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idRegiao);
            rs = stmt.executeQuery();

            while (rs.next()) {
                oProcedencia = new Procedencia(rs.getInt("idprocedencia"),
                        rs.getString("descricao"),
                        rs.getString("situacao"));
            }
            return oProcedencia;
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

        String sql = "SELECT * FROM procedencia";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Procedencia oProcedencia = new Procedencia(rs.getInt("idprocedencia"),
                        rs.getString("descricao"),
                        rs.getString("situacao"));
                
                resultado.add(oProcedencia);
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
