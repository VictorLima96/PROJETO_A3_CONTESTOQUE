package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.sqlite.ConectorSQLite;
import model.Produto;

public class ProdutoDAO {

    public void salvar(Produto p) {
        String sql = "INSERT INTO TB_Produtos (nome, quantidade) VALUES (?,?)";
        try (Connection c = ConectorSQLite.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getQuantidade());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produto buscarUm(String codProduto) {
        String sql = "SELECT * FROM TB_Produtos WHERE codigoProduto = ?";
        try (Connection c = ConectorSQLite.obterConexao()) {
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, codProduto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Produto(
                        rs.getString("nome"),
                        rs.getInt("quantidade"));
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
            while (rs.next()) lista.add(new Produto(
                    rs.getString("nome"),
                    rs.getInt("quantidade")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}