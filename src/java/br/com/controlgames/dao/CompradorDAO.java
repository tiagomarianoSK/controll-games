
package br.com.controlgames.dao;

import br.com.controlgames.model.Comprador;
import br.com.controlgames.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompradorDAO implements GenericDAO {

    private Connection conexao;

    public CompradorDAO() throws Exception {

        try {
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Comprador oComprador = (Comprador) objeto;
        Boolean retorno = false;

        if (oComprador.getIdComprador() == 0) {
            int idComprador = this.verificarCpf(oComprador.getCpf());
            if (idComprador == 0) {
                retorno = this.inserir(oComprador);
            } else {
                retorno = this.alterar(oComprador);
            }
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Comprador oComprador = (Comprador) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into comprador (idcomprador, observacao, situacao) values (?, ?, ?)";

        try {
            PessoaDAO oPessoaDAO = new PessoaDAO();
            int idPessoa = oPessoaDAO.cadastrar(oComprador);

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            stmt.setString(2, oComprador.getObservacao());
            stmt.setString(3, "A");
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao incluir comprador! Erro " + ex.getMessage());
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
        Comprador oComprador = (Comprador) objeto;
        PreparedStatement stmt = null;
        String sql = "update comprador set  observacao=? where idcomprador=?";

        try {
            PessoaDAO oPessoaDAO = new PessoaDAO();
            oPessoaDAO.cadastrar(oComprador);

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oComprador.getObservacao());
            stmt.setInt(2, oComprador.getIdComprador());
            stmt.execute();

            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar comprador! Erro: " + ex.getMessage());
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
        Comprador oComprador = (Comprador) objeto;
        PreparedStatement stmt = null;

        String situacao = "A";
        if (oComprador.getSituacao().equals(situacao)) {
            situacao = "I";
        } else {
            situacao = "A";
        }
        String sql = "update comprador set situacao=? where idcomprador=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, oComprador.getIdComprador());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao ativar/desativar comprador! Erro: " + ex.getMessage());
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
        int idComprador = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Comprador oComprador = null;
        String sql = "Select * from comprador c, pessoa p where c.idcomprador = p.idpessoa and c.idcomprador=?";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idComprador);
            rs = stmt.executeQuery();

            while (rs.next()) {
                oComprador = new Comprador(rs.getInt("idcomprador"),
                        rs.getString("observacao"),
                        rs.getString("situacao"),
                        rs.getInt("idpessoa"),
                        rs.getString("cpf"),
                        rs.getString("nome"));
            }
            return oComprador;
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar comprador! Erro " + ex.getMessage());
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

        String sql = "Select * from comprador c, pessoa p where c.idcomprador = p.idpessoa order by idpessoa";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Comprador oComprador = new Comprador(rs.getInt("idcomprador"),
                        rs.getString("observacao"),
                        rs.getString("situacao"),
                        rs.getInt("idpessoa"),
                        rs.getString("cpf"),
                        rs.getString("nome"));
                resultado.add(oComprador);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar comprador! Erro " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(conexao, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parâmetros de conexão! Erro: " + ex.getMessage());
            }
        }
        return resultado;
    }

    public int verificarCpf(String cpf) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idComprador = 0;
        String sql = "Select * from comprador c, pessoa p, where c.idcomprador = p.idpessoa and p.cpf=?;";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();

            while (rs.next()) {
                idComprador = rs.getInt("idcomprador");
            }
            return idComprador;
        } catch (SQLException ex) {
            System.out.println("Problemas ai carregar pessoa! Erro: " + ex.getMessage());
            return idComprador;
        }
    }

}
