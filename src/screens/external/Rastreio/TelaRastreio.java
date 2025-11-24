package screens.external.Rastreio;

import dao.ProdutoDAO;
import dao.UsuarioDAO;

import javax.swing.*;

import model.Produto;

public class TelaRastreio extends JFrame {
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private final JTextField txtCodProduto = new JTextField();
    private final JTextField txtNomeProduto = new JTextField();
    private final JTextField txtEstoqueProduto = new JTextField();
    private final JTextField txtStatusProduto = new JTextField();
    private final JTextField txtContato = new JTextField();

    private Produto produto = new Produto();
    
    public TelaRastreio() {
        super("Rastreio de Produto");
        setSize(400, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblCodProduto = new JLabel("Código do produto:");
        lblCodProduto.setBounds(30, 50, 140, 25);
        add(lblCodProduto);
        txtCodProduto.setBounds(160, 50, 200, 25);
        add(txtCodProduto);

        JButton btnRastrear = new JButton("Rastrear");
        btnRastrear.setBounds(30, 100, 120, 28);
        getRootPane().setDefaultButton(btnRastrear);
        btnRastrear.addActionListener(this::buscarProduto);
        add(btnRastrear);

        JLabel lblDescricaoProduto = new JLabel("Nome:");
        lblDescricaoProduto.setBounds(30, 150, 100, 25);
        add(lblDescricaoProduto);
        txtNomeProduto.setBounds(130, 150, 220, 25);
        txtNomeProduto.setEditable(false);
        add(txtNomeProduto);

        JLabel lblEstoqueProduto = new JLabel("Estoque:");
        lblEstoqueProduto.setBounds(30, 200, 100, 25);
        add(lblEstoqueProduto);
        txtEstoqueProduto.setBounds(130, 200, 220, 25);
        txtEstoqueProduto.setEditable(false);
        add(txtEstoqueProduto);

        JLabel lblStatusProduto = new JLabel("Status:");
        lblStatusProduto.setBounds(30, 250, 100, 25);
        add(lblStatusProduto);
        txtStatusProduto.setBounds(130, 250, 220, 25);
        txtStatusProduto.setEditable(false);
        txtStatusProduto.setToolTipText("Entre em contato com o fornecedor para mais informações.");
        add(txtStatusProduto);

        JLabel lblContato = new JLabel("Contato:");
        lblContato.setBounds(30, 300, 100, 25);
        txtContato.setBounds(130, 300, 220, 25);
        txtContato.setEditable(false);

        if(produto.getCodProduto() != null) {
            var usuario = usuarioDAO.obterUm(produto.getCodUsuario());
            if(usuario != null) {
                txtContato.setText(usuario.getEmail());
                add(lblContato);
                add(txtContato);
            }
        
        } 
    }

    private void buscarProduto(java.awt.event.ActionEvent ev) {
        String codigo = txtCodProduto.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Informe o código do produto.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            txtCodProduto.requestFocus();
            return;
        }

        try {
            Produto produto = produtoDAO.buscarUm(codigo);

            if (produto == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Produto não encontrado.",
                        "Aviso",
                        JOptionPane.INFORMATION_MESSAGE
                );
                limparCampos();
                return;
            }

            this.produto = produto;

            txtNomeProduto.setText(produto.getNome());
            txtEstoqueProduto.setText(String.valueOf(produto.getQuantidade()));
            txtStatusProduto.setText(produto.getStatus());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao buscar produto: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limparCampos() {
        txtNomeProduto.setText("");
        txtStatusProduto.setText("");
        txtContato.setText("");
    }
}