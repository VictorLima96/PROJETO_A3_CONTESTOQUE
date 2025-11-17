package ui;

import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

    private final JTextField txtUsuario;
    private final JPasswordField txtSenha;
    UsuarioDAO dao = new UsuarioDAO();

    public TelaLogin() {
        setTitle("Login");
        setSize(350, 200);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblUsu = new JLabel("Usuário:");
        lblUsu.setBounds(30, 30, 80, 25);
        add(lblUsu);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(110, 30, 180, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 70, 80, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(110, 70, 180, 25);
        add(txtSenha);

        JButton btn = new JButton("Entrar");
        getRootPane().setDefaultButton(btn);
        btn.setBounds(110, 110, 120, 28);
        btn.addActionListener(this::logar);
        add(btn);
    }

    private void logar(ActionEvent e) {
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