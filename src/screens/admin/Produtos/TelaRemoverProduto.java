package screens.admin.Produtos;
    
import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;

public class TelaRemoverProduto extends JFrame {
    private final ProdutoDAO dao = new ProdutoDAO();

    private Produto produtoEncontrado;

    private final JTextField txtCodProduto = new JTextField();

    private final JTextField txtNomeProduto = new JTextField();
    private final JTextField txtStatusProduto = new JTextField();

    private final JButton btnBuscar = new JButton("Buscar Produto");


    private final JButton btnRemover = new JButton("Remover");

    private Runnable onRemocao;

    TelaRemoverProduto(Runnable onRemocao) {
        this();
        this.onRemocao = onRemocao;
    }

    public TelaRemoverProduto() {
        super("Remover Produto");
        setSize(500, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblCodProduto = new JLabel("Código do produto:");
        lblCodProduto.setBounds(30, 50, 120, 25);
        add(lblCodProduto);
        txtCodProduto.setBounds(160, 50, 250, 25);
        txtCodProduto.setText("Digite o código do produto");
        add(txtCodProduto);

        JLabel lblNomeProduto = new JLabel("Nome:");
        lblNomeProduto.setBounds(30, 100, 100, 25);
        add(lblNomeProduto);
        txtNomeProduto.setBounds(160, 100, 250, 25);
        txtNomeProduto.setEditable(produtoEncontrado != null);
        add(txtNomeProduto);

        JLabel lblStatusProduto = new JLabel("Status:");
        lblStatusProduto.setBounds(30, 150, 100, 25);
        add(lblStatusProduto);
        txtStatusProduto.setBounds(160, 150, 250, 25);
        txtStatusProduto.setEditable(produtoEncontrado != null);
        add(txtStatusProduto);

        btnRemover.setBounds(160, 200, 100, 30);
        getRootPane().setDefaultButton(btnRemover);
        btnRemover.addActionListener(this::buttonRemoverActionPerformed);
        btnRemover.setEnabled(produtoEncontrado != null);
        add(btnRemover);

        btnBuscar.setBounds(320, 200, 130, 25);
        btnBuscar.addActionListener(this::buttonBuscarActionPerformed);
        add(btnBuscar);

        setVisible(true);
    }

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent ev) {
        String codProduto = txtCodProduto.getText().trim();
        Produto produto = dao.buscarUm(codProduto);
        
        btnBuscar.setText("Buscando..");
        
        if (produto == null) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado!");
            btnBuscar.setText("Buscar Produto");
            return;
        } else {
        }

        txtNomeProduto.setText(produto.getNome());
        txtStatusProduto.setText(produto.getStatus());
        btnRemover.setEnabled(true);

        this.produtoEncontrado = produto;
        btnBuscar.setText("Buscar Produto");
    }

    private void buttonRemoverActionPerformed(java.awt.event.ActionEvent ev) {
        String codProduto = txtCodProduto.getText().trim();

        if(produtoEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Nenhum produto selecionado para remoção!");
            return;
        }

        try {
            dao.removerUm(codProduto);

            JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");

            if (onRemocao != null) {
                onRemocao.run();
            }
            
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }


}