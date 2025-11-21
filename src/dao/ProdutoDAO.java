package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.sqlite.ConectorSQLite;
import model.Produto;

public class ProdutoDAO {

    public void salvar(Produto p) {
        String sql = "INSERT INTO produtos (nome, quantidade) VALUES (?,?)";
        try (Connection c = ConectorSQLite.obterConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getQuantidade());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> obterTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
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