package screens.admin.Produtos;
    
import dao.ProdutoDAO;
import screens.admin.MenuAdminInicio;

import java.awt.*;
import javax.swing.*;

public class TelaCadastrarProduto extends JFrame {
    private final ProdutoDAO dao = new ProdutoDAO();

    public TelaCadastrarProduto() {
        super("Administração de Produtos");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelBotoes = new JPanel(new FlowLayout());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            new MenuAdminInicio().setVisible(true);
            dispose();
        });
        panelBotoes.add(btnVoltar);

        JButton btnCadastrar = new JButton("Cadastrar Produto");
        btnCadastrar.addActionListener(e -> new TelaCadastrarProduto().setVisible(true));
        panelBotoes.add(btnCadastrar);

        DefaultListModel<String> model = new DefaultListModel<>();
        dao.buscarTodos().forEach(p -> model.addElement(p.getNome() + " - Quantidade: " + p.getQuantidade()));
        JList<String> lista = new JList<>(model);

        add(panelBotoes, BorderLayout.NORTH);
        add(new JScrollPane(lista), BorderLayout.CENTER);
        setVisible(true);
    }
}