package screens;

import java.awt.event.*;
import javax.swing.*;

import screens.auth.TelaLogin;
import screens.external.Rastreio.TelaRastreio;
import utils.DarkModeToggle;

public class TelaInicio extends JFrame {
    public TelaInicio() {
        super("Início");

        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JTextPane txtBemVindo = new JTextPane();
        txtBemVindo.setText("Bem-vindo ao ConteEstoque. Uma plataforma para rastreamento de produto de forma facilitada para você e sua empresa!");
        txtBemVindo.setEditable(false);
        txtBemVindo.setBounds(25, 50, 350, 60);
        txtBemVindo.setOpaque(false);
        add(txtBemVindo);

        JTextPane txtInformativo = new JTextPane();
        txtInformativo.setText("Aqui você pode rastrear seus produtos ou fazer login como administrador para gerenciar o seu estoque.");
        txtInformativo.setEditable(false);
        txtInformativo.setBounds(25, 110, 350, 30);
        add(txtInformativo);

        JButton btnRastrear = new JButton("Quero rastrear um produto");
        btnRastrear.setBounds(100, 150, 200, 50);
        btnRastrear.addActionListener(e -> {
            new TelaRastreio().setVisible(true);
            dispose();
        });
        add(btnRastrear);

        JButton btnLoginAdmin = new JButton("Login Administrativo");
        btnLoginAdmin.setBounds(100, 210, 200, 28);
        btnLoginAdmin.setBorder(BorderFactory.createSoftBevelBorder(1));
        btnLoginAdmin.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });

        add(btnLoginAdmin);

        // Dark mode
        DarkModeToggle.track(this);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("control shift D"), "dark");
        getRootPane().getActionMap().put("dark", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) {
                DarkModeToggle.toggle();
            }
        });
    }
}