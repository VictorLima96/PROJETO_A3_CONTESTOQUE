package screens.admin.Produtos;

import dao.ProdutoDAO;
import model.Produto;

import java.awt.*;
import javax.swing.*;
import java.util.List;
public class TelaListarProduto extends JFrame {
    private final ProdutoDAO dao = new ProdutoDAO();

    private List<Produto> produtos;
    private JList<String> listaProdutos = new JList<>();

    public TelaListarProduto() {
        super("Administrar Produtos");
        setSize(800, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buscarProdutos();

        JPanel panelBotoes = new JPanel(new FlowLayout());

        JButton btnCadastrar = new JButton("Cadastrar Produto");
        btnCadastrar.addActionListener(e -> new TelaCadastrarProduto(() -> {
            buscarProdutos();
        }).setVisible(true));
        panelBotoes.add(btnCadastrar);

        JButton btnEditar = new JButton("Editar Produto");
        btnEditar.addActionListener(e -> new TelaEditarProduto(() -> {
            buscarProdutos();
        }).setVisible(true));
        panelBotoes.add(btnEditar);

        JButton btnRemover = new JButton("Remover Produto");
        btnRemover.addActionListener(e -> new TelaRemoverProduto(() -> {
            buscarProdutos();
        }).setVisible(true));
        panelBotoes.add(btnRemover);

        add(panelBotoes, BorderLayout.NORTH);
        add(new JScrollPane(listaProdutos), BorderLayout.CENTER);
        setVisible(true);
    }

    private void buscarProdutos() {
        produtos = dao.buscarTodos();
        
        DefaultListModel<String> model = new DefaultListModel<>();
        this.produtos.forEach(p -> model.addElement("Código: " + p.getCodProduto() + " | Produto: " + p.getNome() + " | Quantidade em Estoque: " + p.getQuantidade() + " | Status: " + p.getStatus()));

        listaProdutos.setModel(model);
        listaProdutos.repaint();
    }
}