package database.sqlite;

import java.sql.Connection;


public final class TabelasSQLite {
    final Connection conexao;

    public static final String TABELA_USUARIOS = """
        CREATE TABLE IF NOT EXISTS TB_Usuarios (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nomeUsuario TEXT UNIQUE NOT NULL,
            senha TEXT NOT NULL,
            telefone TEXT NULL,
            isAdmin BOOLEAN NOT NULL
        );
        """;

    public static final String TABELA_PRODUTOS = """
        CREATE TABLE IF NOT EXISTS TB_Produtos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nome TEXT NOT NULL,
            quantidade INTEGER NOT NULL,
            codProduto TEXT NOT NULL,
            codUsuario INTEGER NOT NULL,
            status TEXT NOT NULL
        );
        """;

    public static final String INSERIR_ADMIN_PADRAO = """
        INSERT OR IGNORE INTO TB_Usuarios (nomeUsuario, senha, isAdmin)
        VALUES ('admin', '12345', 1);
        """;
    

    public TabelasSQLite(Connection conexao) {
        this.conexao = conexao;

        inicializarTabela(TABELA_USUARIOS);
        inicializarTabela(TABELA_PRODUTOS);
        inicializarTabela(INSERIR_ADMIN_PADRAO);
    }


    private void inicializarTabela(String ddl) {
        try {
            conexao.createStatement().execute(ddl);
        } catch (Exception e) {
            System.err.println("Erro ao inicializar tabela: " + e.getLocalizedMessage());
        }
    }
}
