package screens.auth;

import dao.UsuarioDAO;

import java.awt.event.*;
import javax.swing.*;

import model.Usuario;
import screens.admin.MenuInicio;
import utils.DarkModeToggle;

public class TelaLogin extends JFrame {
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtSenha = new JPasswordField();
    private final UsuarioDAO dao = new UsuarioDAO();

    public TelaLogin() {
        super("Login");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JButton btnRastrear = new JButton("Rastrear Encomenda");
        btnRastrear.setBounds(200, 20, 180, 20);
        btnRastrear.addActionListener(e -> {
            // new TelaRastreamento().setVisible(true);
        });
        add(btnRastrear);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(30, 70, 80, 25);
        add(lblUsuario);
        txtUsuario.setBounds(110, 70, 250, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 100, 80, 25);
        add(lblSenha);
        txtSenha.setBounds(110, 100, 250, 25);
        add(txtSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(110, 140, 120, 28);
        getRootPane().setDefaultButton(btnEntrar);
        btnEntrar.addActionListener(this::logar);
        add(btnEntrar);

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

    private void logar(java.awt.event.ActionEvent ev) {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());
        Usuario usuarioEncontrado = dao.autenticarUsuario(usuario, senha);

        System.out.println("Tentativa de login: " + usuarioEncontrado);

        if (usuarioEncontrado != null) {
            JOptionPane.showMessageDialog(this, "Bem-vindo!");
            new MenuInicio();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário/Senha incorretos!");
        }
    }
}