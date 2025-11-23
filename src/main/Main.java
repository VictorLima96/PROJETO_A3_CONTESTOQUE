package main;

import javax.swing.JFrame;

import database.sqlite.*;
import screens.TelaInicio;

public class Main {
    private static void inicializarDependencias() {
        var conn = ConectorSQLite.obterConexao();
        new TabelasSQLite(conn);
    }

    private static void inicializarTelaInicio(JFrame tela) {
        tela.setVisible(true);
    }

    public static void main(String[] args) {
        inicializarDependencias();
        inicializarTelaInicio(new TelaInicio());
    }
}