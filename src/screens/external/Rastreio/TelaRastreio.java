package screens.external.Rastreio;

import dao.ProdutoDAO;
import dao.UsuarioDAO;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;

import javax.swing.*;

import model.Produto;
import screens.TelaInicio;

public class TelaRastreio extends JFrame {
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private final JTextField txtCodProduto = new JTextField();
    private final JTextField txtNomeProduto = new JTextField();
    private final JTextField txtAlturaProduto = new JTextField();
    private final JTextField txtLarguraProduto = new JTextField();
    private final JTextField txtProfundidadeProduto = new JTextField();
    private final JTextField txtStatusProduto = new JTextField();
    private final JTextField txtContato = new JTextField();

    private Produto produto = new Produto();
    
    public TelaRastreio() {
        super("Rastreio de Produto");
        setSize(400, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblCodProduto = new JLabel("Código do produto:");
        lblCodProduto.setBounds(30, 70, 140, 25);
        add(lblCodProduto);
        txtCodProduto.setBounds(160, 70, 200, 25);
        add(txtCodProduto);

        JButton btnRastrear = new JButton("Rastrear");
        btnRastrear.setBounds(30, 120, 120, 28);
        getRootPane().setDefaultButton(btnRastrear);
        btnRastrear.addActionListener(this::buscarProduto);
        add(btnRastrear);

        JLabel lblDescricaoProduto = new JLabel("Descrição:");
        lblDescricaoProduto.setBounds(30, 190, 100, 25);
        add(lblDescricaoProduto);
        txtNomeProduto.setBounds(140, 190, 220, 25);
        txtNomeProduto.setEditable(false);
        add(txtNomeProduto);

        JLabel lblAlturaProduto = new JLabel("Altura (cm):");
        lblAlturaProduto.setBounds(30, 230, 100, 25);
        add(lblAlturaProduto);
        txtAlturaProduto.setBounds(140, 230, 220, 25);
        txtAlturaProduto.setEditable(false);
        add(txtAlturaProduto);

        JLabel lblLarguraProduto = new JLabel("Largura (cm):");
        lblLarguraProduto.setBounds(30, 270, 100, 25);
        add(lblLarguraProduto);
        txtLarguraProduto.setBounds(140, 270, 220, 25);
        txtLarguraProduto.setEditable(false);
        add(txtLarguraProduto);

        JLabel lblProfundidadeProduto = new JLabel("Profundidade (cm):");
        lblProfundidadeProduto.setBounds(30, 310, 130, 25);
        add(lblProfundidadeProduto);
        txtProfundidadeProduto.setBounds(160, 310, 200, 25);
        txtProfundidadeProduto.setEditable(false);
        add(txtProfundidadeProduto);

        JLabel lblStatusProduto = new JLabel("Status:");
        lblStatusProduto.setBounds(30, 350, 100, 25);
        add(lblStatusProduto);
        txtStatusProduto.setBounds(140, 350, 220, 25);
        txtStatusProduto.setEditable(false);
        txtStatusProduto.setToolTipText("Entre em contato com o fornecedor para mais informações.");
        add(txtStatusProduto);

        JLabel lblContato = new JLabel("Contato:");
        lblContato.setBounds(30, 390, 100, 25);
        txtContato.setBounds(140, 390, 220, 25);
        txtContato.setEditable(false);

        if(produto.getCodRastreio() != null) {
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
            txtAlturaProduto.setText(String.valueOf(produto.getAltura()));
            txtLarguraProduto.setText(String.valueOf(produto.getLargura()));
            txtProfundidadeProduto.setText(String.valueOf(produto.getProfundidade()));
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
        txtAlturaProduto.setText("");
        txtLarguraProduto.setText("");
        txtProfundidadeProduto.setText("");
        txtStatusProduto.setText("");
        txtContato.setText("");
    }
}