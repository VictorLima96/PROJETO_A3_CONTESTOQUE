package screens.auth;

import dao.UsuarioDAO;

import java.awt.event.*;
import javax.swing.*;

import model.Usuario;
import screens.external.MenuPublicoInicio;
import utils.DarkModeToggle;

public class TelaCadastro extends JFrame {
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtSenha = new JPasswordField();
    private final JPasswordField txtConfirmSenha = new JPasswordField();

    public TelaCadastro() {
        super("Cadastro de Usuário");
        setSize(440, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(30, 60, 80, 25);
        add(lblUsuario);
        txtUsuario.setBounds(160, 60, 250, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 90, 80, 25);
        add(lblSenha);
        txtSenha.setBounds(160, 90, 250, 25);
        add(txtSenha);

        JLabel lblConfirmSenha = new JLabel("Confirmar Senha:");
        lblConfirmSenha.setBounds(30, 120, 120, 25);
        add(lblConfirmSenha);
        txtConfirmSenha.setBounds(160, 120, 250, 25);
        add(txtConfirmSenha);
        
        

        JButton btnRegistrar = new JButton("Registrar-se");
        btnRegistrar.setBounds(160, 170, 120, 28);
        getRootPane().setDefaultButton(btnRegistrar);
        btnRegistrar.addActionListener(this::buttonCadastrarActionPerformed);
        add(btnRegistrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(290, 170, 120, 28);
        btnVoltar.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });
        add(btnVoltar);

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
    private void buttonCadastrarActionPerformed(ActionEvent ev) {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword());
        var dao = new UsuarioDAO();

        if(dao.obterUmPorNome(usuario) != null) {
            JOptionPane.showMessageDialog(this, "Nome de usuário já existe!");
            return;
        }

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        if (senha.length() < 3) {
            JOptionPane.showMessageDialog(this, "Senha deve ter pelo menos 3 caracteres!");
            return;
        }

        dao.criarUsuario(new Usuario().setNomeUsuario(usuario).setSenha(senha).setIsAdmin(false));
        JOptionPane.showMessageDialog(this, "✅ Usuário cadastrado com sucesso!");
        txtUsuario.setText("");
        txtSenha.setText("");
        txtUsuario.requestFocus();

        new MenuPublicoInicio();

        dispose();
    }
}