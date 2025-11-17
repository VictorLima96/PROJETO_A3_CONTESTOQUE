package ui;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import java.awt.*;

public class TelaAdminProdutos extends JFrame {

    private final ProdutoDAO dao = new ProdutoDAO();

    public TelaAdminProdutos() {
        setTitle("Administração de Produtos");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Painel superior com botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            new TelaEstoque().setVisible(true);
            dispose();
        });
        
        JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
        btnCadastrarProduto.addActionListener(e -> {
            new TelaCadastroProduto().setVisible(true);
        });
        
        JButton btnAtualizar = new JButton("Atualizar Lista");
        btnAtualizar.addActionListener(e -> atualizarLista());
        
        panelBotoes.add(btnVoltar);
        panelBotoes.add(btnCadastrarProduto);
        panelBotoes.add(btnAtualizar);
        
        // Lista de produtos
        DefaultListModel<String> model = new DefaultListModel<>();
        atualizarModelo(model);
        
        JList<String> lista = new JList<>(model);
        
        // Adicionar componentes
        add(panelBotoes, BorderLayout.NORTH);
        add(new JScrollPane(lista), BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private void atualizarModelo(DefaultListModel<String> model) {
        model.clear();
        for (Produto p : dao.getAll()) {
            model.addElement(p.getNome() + " - Quantidade: " + p.getQuantidade());
        }
    }
    
    private void atualizarLista() {
        DefaultListModel<String> novoModelo = new DefaultListModel<>();
        atualizarModelo(novoModelo);
        
        // Atualiza a lista existente
        Component[] components = getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof JScrollPane scrollPane) {
                JList<String> novaLista = new JList<>(novoModelo);
                scrollPane.setViewportView(novaLista);
                break;
            }
        }
        revalidate();
        repaint();
        
        JOptionPane.showMessageDialog(this, "Lista atualizada!");
    }
}