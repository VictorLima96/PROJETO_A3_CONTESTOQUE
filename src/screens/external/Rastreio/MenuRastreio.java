package screens.external.Rastreio;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuRastreio {
    final JFrame parentFrame;

    JMenu menu = new JMenu("Rastreio");
    final JMenuItem menuItemRastreio = new JMenuItem("Rastrear Produto");    
    
    public MenuRastreio(JFrame parentFrame, JMenuBar menuBar) {
        menuItemRastreio.addActionListener(this::buttonRastreioActionPerformed);

        menu.add(menuItemRastreio);

        menuBar.add(menu);

        this.parentFrame = parentFrame;
    }

    private void buttonRastreioActionPerformed(java.awt.event.ActionEvent evt) {                                             
        var tela = new  TelaRastreio();
        tela.setVisible(true);
    }
}
