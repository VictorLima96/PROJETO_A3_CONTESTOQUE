package ui;

import dao.UsuarioDAO;
import java.awt.event.*;
import javax.swing.*;
import model.Usuario;
import utils.DarkModeToggle;

public class TelaLogin extends JFrame {
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtSenha = new JPasswordField();
    private final UsuarioDAO dao = new UsuarioDAO();

    public TelaLogin() {
        super("Login");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(30, 30, 80, 25);
        add(lblUsuario);
        txtUsuario.setBounds(110, 30, 180, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 70, 80, 25);
        add(lblSenha);
        txtSenha.setBounds(110, 70, 180, 25);
        add(txtSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(110, 110, 120, 28);
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
        Usuario u = dao.login(usuario, senha);
        if (u != null) {
            JOptionPane.showMessageDialog(this, "Bem-vindo!");
            new TelaEstoque().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário/Senha incorretos!");
        }
    }
}