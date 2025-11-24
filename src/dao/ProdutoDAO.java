package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.sqlite.ConectorSQLite;
import model.Produto;
import utils.RastreioGen;

public class ProdutoDAO {
    public Produto cadastrarUm(Produto produto) {
        if(this.buscarUm(produto.getCodProduto()) != null) {
            produto.setCodProduto(RastreioGen.gerarCodigoRastreio());
        }

        String sql = "INSERT INTO TB_Produtos (nome, quantidade, codProduto, codUsuario, status) VALUES (?,?,?,?,?)";
        try (Connection c = ConectorSQLite.obterConexao();
             PreparedStatement st = c.prepareStatement(sql)) {
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getQuantidade());
            st.setString(3, produto.getCodProduto());
            st.setInt(4, produto.getCodUsuario());
            st.setString(5, produto.getStatus());
            st.executeUpdate();
            return produto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto atualizarUm(Produto produto) {
        if(this.buscarUm(produto.getCodProduto()) == null) {
            throw new IllegalArgumentException("Produto não encontrado para atualização.");
        }

        String sql = "UPDATE TB_Produtos SET nome=?, quantidade=?, status=? WHERE codProduto=?";

        try (Connection c = ConectorSQLite.obterConexao();
             PreparedStatement st = c.prepareStatement(sql)) {
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getQuantidade());
            st.setString(3, produto.getStatus());
            st.setString(4, produto.getCodProduto());
            st.executeUpdate();
            return produto;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void removerUm(String codProduto) {
        if(this.buscarUm(codProduto) == null) {
            throw new IllegalArgumentException("Produto não encontrado para remoção.");
        }

        String sql = "DELETE FROM TB_Produtos WHERE codProduto = ?";
        
        try (Connection c = ConectorSQLite.obterConexao();
            PreparedStatement st = c.prepareStatement(sql)) {
            st.setString(1, codProduto);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produto buscarUm(String codProduto) {
        String sql = "SELECT * FROM TB_Produtos WHERE codProduto = ?";
        try (Connection c = ConectorSQLite.obterConexao()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, codProduto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Produto().setId(rs.getInt("id"))
                        .setNome(rs.getString("nome"))
                        .setQuantidade(rs.getInt("quantidade"))
                        .setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Produto> buscarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM TB_Produtos";
        try (Connection c = ConectorSQLite.obterConexao();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) lista.add(new Produto().setId(rs.getInt("id"))
                    .setNome(rs.getString("nome"))
                    .setCodProduto(rs.getString("codProduto"))
                    .setQuantidade(rs.getInt("quantidade"))
                    .setStatus(rs.getString("status"))
            );
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}