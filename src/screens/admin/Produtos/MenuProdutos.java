package screens.admin.Produtos;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuProdutos {
    final JFrame parentFrame;

    final JMenuItem menuListarProdutos = new JMenuItem("Administrar Produtos");
    
    
    public MenuProdutos(JFrame parentFrame, JMenuBar menuBar) {
        JMenu menuProdutos = new JMenu("Produtos");

        menuListarProdutos.addActionListener(this::buttonListarProdutosActionPerformed);

        menuProdutos.add(menuListarProdutos);
    
        menuBar.add(menuProdutos);
        
        this.parentFrame = parentFrame;
    }

    private void buttonListarProdutosActionPerformed(java.awt.event.ActionEvent evt) {             
        var tela = new TelaListarProduto();
        tela.setVisible(true);
    }
}
