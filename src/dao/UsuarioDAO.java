package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.sqlite.ConectorSQLite;
import model.Usuario;

public class UsuarioDAO {

    public void criarUsuario(Usuario u) {
        String sql = "INSERT INTO TB_Usuarios (nomeUsuario, senha, isAdmin) VALUES (?,?,?)";
        try (Connection c = ConectorSQLite.obterConexao();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, u.getNomeUsuario());
            p.setString(2, u.getSenha());
            p.setBoolean(3, u.getIsAdmin());
            p.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao criar o usuário.");
        }
    }

    public Usuario editarUsuario(String codUsuario, String nomeUsuario, String senha, boolean isAdmin) {
        String sql = "UPDATE TB_Usuarios SET nomeUsuario=?, senha=?, isAdmin=? WHERE codUsuario=?";

        try (Connection c = ConectorSQLite.obterConexao();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, nomeUsuario);
            p.setString(2, senha);
            p.setBoolean(3, isAdmin);
            p.setString(4, codUsuario.toString());
            int rows = p.executeUpdate();

            if (rows > 0) {
                return obterUm(codUsuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar o usuário.");
        }
        return null;
    }

    public Usuario autenticarUsuario(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM TB_Usuarios WHERE nomeUsuario=? AND senha=?";
        try (Connection c = ConectorSQLite.obterConexao();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, nomeUsuario);
            p.setString(2, senha);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return new Usuario()
                        .setId(rs.getString("id"))
                        .setNomeUsuario(rs.getString("nomeUsuario"))
                        .setSenha(rs.getString("senha"))
                        .setIsAdmin(rs.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao autenticar o usuário.");
        }
        return null;
    }

    public Usuario alterarSenha(String codUsuario, String senhaAtual, String novaSenha) {
        String sql = "UPDATE TB_Usuarios SET senha=? WHERE codUsuario=? AND senha=?";

        try (Connection c = ConectorSQLite.obterConexao();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, novaSenha);
            p.setString(2, codUsuario.toString());
            p.setString(3, senhaAtual);
            int rows = p.executeUpdate();
            if (rows > 0) {
                Usuario usuario = obterUm(codUsuario);
                if (usuario != null) {
                    usuario.setSenha(novaSenha);
                }
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar a senha.");
        }
        return null;
    }

    public List<Usuario> obterTodos() {

        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM TB_Usuarios";
        try (Connection c = ConectorSQLite.obterConexao();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Usuario()
                        .setId(rs.getString("id"))
                        .setNomeUsuario(rs.getString("nomeUsuario"))
                        .setSenha(rs.getString("senha"))
                        .setIsAdmin(rs.getBoolean("isAdmin")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter a lista de usuários.");
        }
        return lista;
    }

    public Usuario obterUm(String codUsuario) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM TB_Usuarios WHERE codUsuario=?";

        try (Connection c = ConectorSQLite.obterConexao();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, codUsuario.toString());
            ResultSet rs = p.executeQuery();

            while (rs.first()) {
                return usuario
                .setId(rs.getString("id"))
                .setNomeUsuario(rs.getString("nomeUsuario"))
                .setSenha(rs.getString("senha"))
                .setIsAdmin(rs.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao obter o usuário.");
        }
        
        return null;
    }
}