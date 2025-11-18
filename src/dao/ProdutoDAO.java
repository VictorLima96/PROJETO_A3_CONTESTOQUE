package dao;

import database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class ProdutoDAO {

    public void salvar(Produto p) {
        String sql = "INSERT INTO produtos (nome, quantidade) VALUES (?,?)";
        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getQuantidade());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> getAll() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection c = DatabaseManager.getConnection();
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