package ui;

import dao.UsuarioDAO;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TelaCadastroUsuario extends JFrame {

    private final JTextField txtUsuario;
    private final JPasswordField txtSenha;
    private final JCheckBox chkAdmin;

    public TelaCadastroUsuario() {
        setTitle("Cadastro de Usuário");
        setSize(350, 280); // Aumentei a altura para caber os botões
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblUsu = new JLabel("Usuário:");
        lblUsu.setBounds(30, 20, 100, 25);
        add(lblUsu);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(110, 20, 180, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 60, 100, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(110, 60, 180, 25);
        add(txtSenha);

        chkAdmin = new JCheckBox("Administrador");
        chkAdmin.setBounds(110, 95, 150, 25);
        add(chkAdmin);

        // Botões lado a lado
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(50, 140, 120, 30);
        btnCadastrar.addActionListener(this::salvarUsuario);
        add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(180, 140, 120, 30); // Ao lado do botão Cadastrar
        btnVoltar.addActionListener(e -> {
            new TelaEstoque().setVisible(true);
            dispose();
        });
        add(btnVoltar);
    }

    private void salvarUsuario(ActionEvent e) {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword());
        
        // Validações
        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        
        if (senha.length() < 3) {
            JOptionPane.showMessageDialog(this, "Senha deve ter pelo menos 3 caracteres!");
            return;
        }

        boolean admin = chkAdmin.isSelected();
        Usuario novo = new Usuario(usuario, senha, admin);
        UsuarioDAO.salvar(novo);

        JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
        
        // Limpar campos
        txtUsuario.setText("");
        txtSenha.setText("");
        chkAdmin.setSelected(false);
        txtUsuario.requestFocus();
    }
}