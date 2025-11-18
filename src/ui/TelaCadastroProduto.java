package ui;

import dao.ProdutoDAO;
import java.awt.event.*;
import javax.swing.*;
import model.Produto;

public class TelaCadastroProduto extends JFrame {
    private final JTextField txtNome = new JTextField();
    private final JTextField txtQtd = new JTextField();
    private final ProdutoDAO dao = new ProdutoDAO();

    public TelaCadastroProduto() {
        super("Cadastro de Produto");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 20, 100, 25);
        add(lblNome);
        txtNome.setBounds(110, 20, 180, 25);
        add(txtNome);

        JLabel lblQtd = new JLabel("Quantidade:");
        lblQtd.setBounds(30, 60, 100, 25);
        add(lblQtd);
        txtQtd.setBounds(110, 60, 180, 25);
        add(txtQtd);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(50, 110, 120, 30);
        btnSalvar.addActionListener(this::salvar);
        add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(180, 110, 120, 30);
        btnVoltar.addActionListener(e -> {
            new TelaEstoque().setVisible(true);
            dispose();
        });
        add(btnVoltar);
    }

    private void salvar(ActionEvent ev) {
        try {
            String nome = txtNome.getText();
            int qtd = Integer.parseInt(txtQtd.getText());
            dao.salvar(new Produto(nome, qtd));
            JOptionPane.showMessageDialog(this, "✅ Produto salvo com sucesso!");
            txtNome.setText("");
            txtQtd.setText("");
            txtNome.requestFocus();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade deve ser um número!");
        }
    }
}