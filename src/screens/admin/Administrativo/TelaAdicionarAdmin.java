package screens.admin.Administrativo;

import dao.UsuarioDAO;
import java.awt.event.*;
import javax.swing.*;
import model.Usuario;
import screens.admin.MenuInicio;

public class TelaAdicionarAdmin extends JFrame {
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtSenha = new JPasswordField();
    private final JCheckBox chkAdmin = new JCheckBox("Administrador");

    public TelaAdicionarAdmin() {
        super("Cadastro de Usuário");
        setSize(350, 280);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(30, 20, 100, 25);
        add(lblUsuario);
        txtUsuario.setBounds(110, 20, 180, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 60, 100, 25);
        add(lblSenha);
        txtSenha.setBounds(110, 60, 180, 25);
        add(txtSenha);

        chkAdmin.setBounds(110, 100, 150, 25);
        add(chkAdmin);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(50, 140, 120, 30);
        btnCadastrar.addActionListener(this::salvar);
        add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(180, 140, 120, 30);
        btnVoltar.addActionListener(e -> {
            new MenuInicio().setVisible(true);
            dispose();
        });
        add(btnVoltar);
    }

    private void salvar(ActionEvent ev) {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword());
        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        if (senha.length() < 3) {
            JOptionPane.showMessageDialog(this, "Senha deve ter pelo menos 3 caracteres!");
            return;
        }

        new UsuarioDAO().criarUsuario(new Usuario().setNomeUsuario(usuario).setSenha(senha));
        JOptionPane.showMessageDialog(this, "✅ Usuário cadastrado com sucesso!");
        txtUsuario.setText("");
        txtSenha.setText("");
        chkAdmin.setSelected(false);
        txtUsuario.requestFocus();
    }
}