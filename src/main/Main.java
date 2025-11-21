package main;

import javax.swing.JFrame;

import database.sqlite.*;
import ui.TelaLogin;

public class Main {
    private static void inicializarDependencias() {
        new TabelasSQLite(ConectorSQLite.obterConexao());
    }

    private static void inicializarTelaInicio(JFrame tela) {
        tela.setVisible(true);
    }

    public static void main(String[] args) {
        inicializarDependencias();
        inicializarTelaInicio(new TelaLogin());
    }
}