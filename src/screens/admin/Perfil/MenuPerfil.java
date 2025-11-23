package screens.admin.Perfil;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import screens.auth.TelaLogin;

public class MenuPerfil {
    final JFrame parentFrame;

    final JMenuItem menuTrocarSenha = new JMenuItem("Trocar Senha");
    final JMenuItem menuSair = new JMenuItem("Sair");
    
    
    public MenuPerfil(JFrame parentFrame, JMenuBar menuBar) {
        JMenu menuPerfil = new JMenu("Meu Usuário");

        menuSair.addActionListener(this::buttonSairActionPerformed);
        menuTrocarSenha.addActionListener(this::buttonTrocarSenhaActionPerformed);

        menuPerfil.add(menuTrocarSenha);
        menuPerfil.add(menuSair);

        menuBar.add(menuPerfil);
        this.parentFrame = parentFrame;
    }

    private void buttonSairActionPerformed(java.awt.event.ActionEvent evt) {             
        var tela = new TelaLogin();
        tela.setVisible(true);
        parentFrame.dispose();
    }

    private void buttonTrocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // Implementar lógica para trocar senha
    }
}
