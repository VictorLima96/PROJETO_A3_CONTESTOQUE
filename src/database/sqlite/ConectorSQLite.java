package database.sqlite;

import java.sql.*;

public class ConectorSQLite {
    static String URL = "jdbc:sqlite:sistema.db";

    public static Connection obterConexao() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver SQLite não encontrado: " + e.getLocalizedMessage());
            return null;
        } 
        catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getLocalizedMessage());
            return null;
        }
    } 
}