package dao;

import database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDAO {

    public void salvar(Usuario u) {
        String sql = "INSERT INTO usuarios (usuario, senha, admin) VALUES (?,?,?)";
        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, u.getUsuario());
            p.setString(2, u.getSenha());
            p.setBoolean(3, u.isAdmin());
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Usuario login(String usuario, String senha) {
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND senha=?";
        try (Connection c = DatabaseManager.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, usuario);
            p.setString(2, senha);
            ResultSet rs = p.executeQuery();
            if (rs.next()) return new Usuario(
                    rs.getString("usuario"),
                    rs.getString("senha"),
                    rs.getBoolean("admin"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> getAll() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection c = DatabaseManager.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) lista.add(new Usuario(
                    rs.getString("usuario"),
                    rs.getString("senha"),
                    rs.getBoolean("admin")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}