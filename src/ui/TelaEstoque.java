package ui;

import javax.swing.*;
import utils.KonamiListener;

public class TelaEstoque extends JFrame {
    public TelaEstoque() {
        super("Controle de Estoque");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

        JButton btnAdminUsu = new JButton("Administrar Usuários");
        btnAdminUsu.setBounds(180, 190, 220, 40);
        btnAdminUsu.addActionListener(e -> {
            new TelaAdminUsuarios().setVisible(true);
            dispose();
        });
        add(btnAdminUsu);

        JButton btnAdminProd = new JButton("Administrar Produtos");
        btnAdminProd.setBounds(180, 260, 220, 40);
        btnAdminProd.addActionListener(e -> {
            new TelaAdminProdutos().setVisible(true);
            dispose();
        });
        add(btnAdminProd);

        addKeyListener(new KonamiListener(this));
        setFocusable(true);
    }
}