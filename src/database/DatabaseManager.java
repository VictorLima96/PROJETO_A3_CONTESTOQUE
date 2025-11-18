package database;

import java.sql.*;

public final class DatabaseManager {
    private static final String URL = "jdbc:sqlite:sistema.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initDatabase() {
        String ddlUsuario = """
            CREATE TABLE IF NOT EXISTS usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT UNIQUE NOT NULL,
                senha TEXT NOT NULL,
                admin BOOLEAN NOT NULL
            );
            """;
        String ddlProduto = """
            CREATE TABLE IF NOT EXISTS produtos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                quantidade INTEGER NOT NULL
            );
            """;
        String insertAdmin = """
            INSERT OR IGNORE INTO usuarios (usuario, senha, admin)
            VALUES ('admin', '12345', 1);
            """;
        try (Connection c = getConnection(); Statement s = c.createStatement()) {
            s.execute(ddlUsuario);
            s.execute(ddlProduto);
            s.execute(insertAdmin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}