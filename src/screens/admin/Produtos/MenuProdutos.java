package screens.admin.Produtos;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuProdutos {
    final JFrame parentFrame;

    final JMenuItem menuCadastrarProduto = new JMenuItem("Cadastrar Produto");
    final JMenuItem menuEditarProduto = new JMenuItem("Editar Produto");
    final JMenuItem menuAtualizarStatus = new JMenuItem("Atualizar Status do Produto");
    
    
    public MenuProdutos(JFrame parentFrame, JMenuBar menuBar) {
        JMenu menuProdutos = new JMenu("Produtos");

        menuCadastrarProduto.addActionListener(this::buttonCadastrarProdutoActionPerformed);
        menuEditarProduto.addActionListener(this::buttonEditarProdutoActionPerformed);
        menuAtualizarStatus.addActionListener(this::buttonAtualizarStatusActionPerformed);

        menuProdutos.add(menuCadastrarProduto);
        menuProdutos.add(menuEditarProduto);
        menuProdutos.add(menuAtualizarStatus);
        
        menuBar.add(menuProdutos);
        
        this.parentFrame = parentFrame;
    }

    private void buttonCadastrarProdutoActionPerformed(java.awt.event.ActionEvent evt) {             
        var tela = new TelaCadastrarProduto();
        tela.setVisible(true);
        parentFrame.dispose();
    }

    private void buttonEditarProdutoActionPerformed(java.awt.event.ActionEvent evt) {      
        var tela = new TelaEditarProduto();
        tela.setVisible(true);
        parentFrame.dispose();
    }

    private void buttonAtualizarStatusActionPerformed(java.awt.event.ActionEvent evt) {                                             
        var tela = new TelaAtualizarStatusProduto();
        tela.setVisible(true);
        parentFrame.dispose();
    }
}
