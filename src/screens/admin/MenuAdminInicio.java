package screens.admin;

import javax.swing.*;

import screens.admin.Administrativo.MenuAdministrativo;
import screens.admin.Produtos.MenuProdutos;
import screens.shared.Perfil.MenuPerfil;

public class MenuAdminInicio extends JFrame  {

    public MenuAdminInicio() {
        super("ConteEstoque - Painel Administrativo");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
     
        new MenuProdutos(this, menuBar);
        new MenuAdministrativo(this, menuBar);
        new MenuPerfil(this, menuBar);

        setJMenuBar(menuBar);
        
        setVisible(true);
    }
}
