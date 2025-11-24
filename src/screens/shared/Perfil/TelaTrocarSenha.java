package screens.shared.Perfil;

import dao.UsuarioDAO;

import javax.swing.*;

import model.Usuario;

public class TelaTrocarSenha extends JFrame {
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtSenhaAtual = new JPasswordField();
    private final JPasswordField txtSenhaNova = new JPasswordField();
    private final UsuarioDAO dao = new UsuarioDAO();

    public TelaTrocarSenha() {
        super("Alterar senha de usuário");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(30, 70, 80, 25);
        add(lblUsuario);
        txtUsuario.setBounds(110, 70, 250, 25);
        add(txtUsuario);

        JLabel lblSenhaAtual = new JLabel("Senha atual:");
        lblSenhaAtual.setBounds(30, 100, 80, 25);
        add(lblSenhaAtual);
        txtSenhaAtual.setBounds(110, 100, 250, 25);
        add(txtSenhaAtual);

        JLabel lblSenhaNova = new JLabel("Senha nova:");
        lblSenhaNova.setBounds(30, 130, 80, 25);
        add(lblSenhaNova);
        txtSenhaNova.setBounds(110, 130, 250, 25);
        add(txtSenhaNova);

        JButton btnEntrar = new JButton("Trocar senha");
        btnEntrar.setBounds(110, 170, 120, 28);
        getRootPane().setDefaultButton(btnEntrar);
        btnEntrar.addActionListener(this::buttonTrocarSenhaActionPerformed);
        add(btnEntrar);
    }

    private void buttonTrocarSenhaActionPerformed(java.awt.event.ActionEvent ev) {
        String usuario = txtUsuario.getText().trim();
        String senhaAtual = new String(txtSenhaAtual.getPassword());
        String senhaNova = new String(txtSenhaNova.getPassword());
        Usuario user = dao.obterUmPorNome(usuario);

        if (user == null || !user.getSenha().equals(senhaAtual)) {
            JOptionPane.showMessageDialog(this, "Usuário ou senha atual incorretos!");
            return;
        }

        if (senhaNova.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A nova senha não pode ser vazia!");
            return;
        }

        dao.alterarSenha(user.getId(), senhaAtual, senhaNova);

        JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
        dispose();
    }
}