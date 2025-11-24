package screens.admin.Produtos;
    
import dao.ProdutoDAO;
import model.Produto;
import utils.RastreioGen;

import javax.swing.*;

public class TelaCadastrarProduto extends JFrame {
    private final ProdutoDAO dao = new ProdutoDAO();

    private final JTextField txtNomeProduto = new JTextField();
    private final JTextField txtQuantidadeProduto = new JTextField();
    private final JComboBox<String> comboStatusProduto = new JComboBox<String>(new String[] { "Disponível", "Indisponível", "Esgotado", "Em Reposição" });

    private Runnable onCadastro;
    
    public TelaCadastrarProduto(Runnable onCadastro) {
        this();
        this.onCadastro = onCadastro;
    }

    public TelaCadastrarProduto() {
        super("Cadastrar Produto");
        setSize(500, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNomeProduto = new JLabel("Nome:");
        lblNomeProduto.setBounds(30, 50, 100, 25);
        add(lblNomeProduto);
        txtNomeProduto.setBounds(160, 50, 250, 25);
        add(txtNomeProduto);

        JLabel lblQuantidadeProduto = new JLabel("Quantidade:");
        lblQuantidadeProduto.setBounds(30, 100, 100, 25);
        add(lblQuantidadeProduto);
        txtQuantidadeProduto.setBounds(160, 100, 250, 25);
        add(txtQuantidadeProduto);

        JLabel lblStatusProduto = new JLabel("Situação:");
        lblStatusProduto.setBounds(30, 150, 100, 25);
        add(lblStatusProduto);
        comboStatusProduto.setBounds(160, 150, 250, 25);
        add(comboStatusProduto);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(160, 200, 100, 30);
        getRootPane().setDefaultButton(btnCadastrar);
        btnCadastrar.addActionListener(this::buttonCadastrarActionPerformed);
        add(btnCadastrar);

        setVisible(true);
    }


    private void buttonCadastrarActionPerformed(java.awt.event.ActionEvent ev) {
        String nome = txtNomeProduto.getText().trim();
        String quantidadeStr = txtQuantidadeProduto.getText().trim();
        String status = (String) comboStatusProduto.getSelectedItem();

        if (nome.isEmpty() || quantidadeStr.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade deve ser um número inteiro válido!");
            return;
        }

        try {
            var produto = dao.cadastrarUm(new Produto()
            .setCodProduto(RastreioGen.gerarCodigoRastreio())
            .setNome(nome)
            .setQuantidade(quantidade)
            .setStatus(status)
            );

            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso! Com o código de rastreio: " + produto.getCodProduto());
        
            if (onCadastro != null) {
                onCadastro.run();
            }

            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar o produto: " + nome);
            e.printStackTrace();
            return;
        }
    }
}