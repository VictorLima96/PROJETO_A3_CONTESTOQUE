package screens.admin;

import javax.swing.*;

import screens.admin.Administrativo.MenuAdministrativo;
import screens.admin.Perfil.MenuPerfil;
import screens.admin.Produtos.MenuProdutos;

public class MenuInicio extends JFrame  {

    public MenuInicio() {
        super("Estoque");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
     
        new MenuPerfil(this, menuBar);
        new MenuProdutos(this, menuBar);
        new MenuAdministrativo(this, menuBar);

        setJMenuBar(menuBar);
        
        setVisible(true);
    }
}
