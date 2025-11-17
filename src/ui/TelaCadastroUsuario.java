package ui;

import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TelaCadastroUsuario extends JFrame {

    private final JTextField txtNome;
    private final JTextField txtLogin;
    private final JPasswordField txtSenha;


    public TelaCadastroUsuario() {
        setTitle("Cadastro de Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(50, 40, 100, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(150, 40, 180, 25);
        add(txtNome);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(50, 80, 100, 25);
        add(lblLogin);

        txtLogin = new JTextField();
        txtLogin.setBounds(150, 80, 180, 25);
        add(txtLogin);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 120, 100, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(150, 120, 180, 25);
        add(txtSenha);

        JButton btnSalvar = new JButton("Cadastrar");
        btnSalvar.setBounds(130, 200, 120, 35);
        btnSalvar.addActionListener(this::salvarUsuario);
        add(btnSalvar);
    }

    private void salvarUsuario(ActionEvent e) {
        String nome = txtNome.getText();
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());

        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        Usuario usuario = new Usuario(nome, login, senha, false);
        UsuarioDAO.salvar(usuario);
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        dispose();
    }
}