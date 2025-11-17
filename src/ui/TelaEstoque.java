package ui;

import utils.KonamiListener;

import javax.swing.*;

public class TelaEstoque extends JFrame {

    public TelaEstoque() {
        setTitle("Controle de Estoque");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(10, 10, 100, 30);
        btnVoltar.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });
        add(btnVoltar);

        JButton btnCadUsu = new JButton("Cadastrar Usuário");
        btnCadUsu.setBounds(180, 50, 220, 40);
        btnCadUsu.addActionListener(e -> new TelaCadastroUsuario().setVisible(true));
        add(btnCadUsu);

        JButton btnCadProd = new JButton("Cadastrar Produto");
        btnCadProd.setBounds(180, 120, 220, 40);
        btnCadProd.addActionListener(e -> new TelaCadastroProduto().setVisible(true));
        add(btnCadProd);

        // Botão Administração de Usuários
        JButton btnAdminUsuarios = new JButton("Administrar Usuários");
        btnAdminUsuarios.setBounds(180, 190, 220, 40);
        btnAdminUsuarios.addActionListener(e -> {
            new TelaAdminUsuarios().setVisible(true);
            dispose();
        });
        add(btnAdminUsuarios);

        // Botão Administração de Produtos - NOVO
        JButton btnAdminProdutos = new JButton("Administrar Produtos");
        btnAdminProdutos.setBounds(180, 260, 220, 40);
        btnAdminProdutos.addActionListener(e -> {
            new TelaAdminProdutos().setVisible(true);
            dispose();
        });
        add(btnAdminProdutos);

        addKeyListener(new KonamiListener(this));
        setFocusable(true);
    }
}