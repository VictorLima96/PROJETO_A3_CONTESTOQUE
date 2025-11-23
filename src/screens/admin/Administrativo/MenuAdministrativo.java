package screens.admin.Administrativo;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuAdministrativo {
    final JFrame parentFrame;

    final JMenuItem menuAdicionarAdministrador = new JMenuItem("Adicionar Administrador");
    
    
    public MenuAdministrativo(JFrame parentFrame, JMenuBar menuBar) {
        JMenu menuAdministrativo = new JMenu("Administrativo");

        menuAdicionarAdministrador.addActionListener(this::buttonAdicionarAdministradorActionPerformed);

        menuAdministrativo.add(menuAdicionarAdministrador);

        menuBar.add(menuAdministrativo);

        this.parentFrame = parentFrame;
    }

    private void buttonAdicionarAdministradorActionPerformed(java.awt.event.ActionEvent evt) {             
        var tela = new TelaAdicionarAdmin();
        tela.setVisible(true);
        parentFrame.dispose();
    }

}
