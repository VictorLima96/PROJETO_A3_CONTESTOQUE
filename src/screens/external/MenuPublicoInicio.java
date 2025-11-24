package screens.external;

import javax.swing.*;

import screens.external.Rastreio.MenuRastreio;
import screens.shared.Perfil.MenuPerfil;

public class MenuPublicoInicio extends JFrame  {

    public MenuPublicoInicio() {
        super("ConteEstoque");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
     
        new MenuRastreio(this, menuBar);
        new MenuPerfil(this, menuBar);
        setJMenuBar(menuBar);
        
        setVisible(true);
    }
}
