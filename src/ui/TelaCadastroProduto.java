package ui;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TelaCadastroProduto extends JFrame {

    private final JTextField txtNome;
    private final JTextField txtQtd;
    ProdutoDAO dao = new ProdutoDAO();

    public TelaCadastroProduto() {
        setTitle("Cadastro de Produto");
        setSize(350, 250); // Aumentei um pouco a altura para caber os botões
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 20, 100, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(110, 20, 180, 25);
        add(txtNome);

        JLabel lblQtd = new JLabel("Quantidade:");
        lblQtd.setBounds(30, 60, 100, 25);
        add(lblQtd);

        txtQtd = new JTextField();
        txtQtd.setBounds(110, 60, 180, 25);
        add(txtQtd);

        // Painel para os botões ficarem lado a lado
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(50, 110, 120, 30);
        btnSalvar.addActionListener(this::salvarProduto);
        add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(180, 110, 120, 30); // Ao lado do botão Salvar
        btnVoltar.addActionListener(e -> {
            new TelaEstoque().setVisible(true);
            dispose();
        });
        add(btnVoltar);
    }
    
    private void salvarProduto(ActionEvent e) {
        try {
            String nome = txtNome.getText();
            int qtd = Integer.parseInt(txtQtd.getText());

            dao.salvar(new Produto(nome, qtd));

            JOptionPane.showMessageDialog(this, "Produto salvo!");
            
            // Limpar campos em vez de fechar
            txtNome.setText("");
            txtQtd.setText("");
            txtNome.requestFocus(); // Foca no primeiro campo
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade deve ser um número!");
        }
    }
}