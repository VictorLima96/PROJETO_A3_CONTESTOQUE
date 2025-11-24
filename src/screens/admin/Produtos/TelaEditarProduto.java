package screens.admin.Produtos;
    
import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;

public class TelaEditarProduto extends JFrame {
    private final ProdutoDAO dao = new ProdutoDAO();

    private Produto produtoEncontrado;

    private final JTextField txtCodProduto = new JTextField();

    private final JTextField txtNomeProduto = new JTextField();
    private final JTextField txtQuantidadeProduto = new JTextField();
    private final JComboBox<String> comboStatusProduto = new JComboBox<String>(new String[] { "Disponível", "Indisponível", "Esgotado", "Em Reposição" });

    private final JButton btnCadastrar = new JButton("Editar");

    Runnable onEditado;

    public TelaEditarProduto(Runnable onEditado) {
        this();
        this.onEditado = onEditado;
    }

    public TelaEditarProduto() {
        super("Editar Produto");
        setSize(500, 350);
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

        JLabel lblQuantidadeProduto = new JLabel("Quantidade:");
        lblQuantidadeProduto.setBounds(30, 150, 100, 25);
        add(lblQuantidadeProduto);
        txtQuantidadeProduto.setBounds(160, 150, 250, 25);
        txtQuantidadeProduto.setEditable(produtoEncontrado != null);
        add(txtQuantidadeProduto);

        JLabel lblStatusProduto = new JLabel("Situação:");
        lblStatusProduto.setBounds(30, 200, 100, 25);
        add(lblStatusProduto);
        comboStatusProduto.setBounds(160, 200, 250, 25);
        comboStatusProduto.setEnabled(produtoEncontrado != null);
        add(comboStatusProduto);

        btnCadastrar.setBounds(160, 250, 100, 30);
        getRootPane().setDefaultButton(btnCadastrar);
        btnCadastrar.addActionListener(this::buttonEditarActionPerformed);
        btnCadastrar.setEnabled(produtoEncontrado != null);
        add(btnCadastrar);

        JButton btnBuscar = new JButton("Buscar Produto");
        btnBuscar.setBounds(320, 250, 130, 25);
        btnBuscar.addActionListener(this::buttonBuscarActionPerformed);
        add(btnBuscar);

        setVisible(true);
    }

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent ev) {
        String codProduto = txtCodProduto.getText().trim();
        Produto produto = dao.buscarUm(codProduto);
        if (produto == null) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado!");
            return;
        }
        txtNomeProduto.setText(produto.getNome());
        txtQuantidadeProduto.setText(String.valueOf(produto.getQuantidade()));
        comboStatusProduto.setSelectedItem(produto.getStatus());

        txtNomeProduto.setEditable(true);
        txtQuantidadeProduto.setEditable(true);
        comboStatusProduto.setEnabled(true);
        btnCadastrar.setEnabled(true);

        this.produtoEncontrado = produto;
    }

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent ev) {
        String codProduto = txtCodProduto.getText().trim();

        String nome = txtNomeProduto.getText().trim();
        String quantidadeStr = txtQuantidadeProduto.getText().trim();
        String status = (String) comboStatusProduto.getSelectedItem();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeStr);
            if (quantidade < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade deve ser um número inteiro não negativo!");
            return;
        }

        Produto novoProduto = new Produto()
                .setCodProduto(codProduto)
                .setNome(nome)
                .setQuantidade(quantidade)
                .setStatus(status);
                
        dao.atualizarUm(novoProduto);
        JOptionPane.showMessageDialog(this, "Produto editado com sucesso!");

        if (onEditado != null) {
            onEditado.run();
        }

        dispose();
    }


}