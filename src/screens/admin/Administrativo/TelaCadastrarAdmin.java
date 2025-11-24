package screens.admin.Administrativo;

import dao.UsuarioDAO;
import java.awt.event.*;
import javax.swing.*;
import model.Usuario;
public class TelaCadastrarAdmin extends JFrame {
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtSenha = new JPasswordField();
    private final JCheckBox chkAdmin = new JCheckBox("Administrador");

    public TelaCadastrarAdmin() {
        super("Cadastro Administrativo");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(425, 230);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(30, 20, 120, 25);
        add(lblUsuario);
        txtUsuario.setBounds(120, 20, 200, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 60, 120, 25);
        add(lblSenha);
        txtSenha.setBounds(120, 60, 200, 25);
        add(txtSenha);

        chkAdmin.setBounds(120, 100, 150, 25);
        add(chkAdmin);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(120, 140, 100, 30);
        btnCadastrar.addActionListener(this::buttonCadastrarAdminActionPerformed);
        getRootPane().setDefaultButton(btnCadastrar);
        add(btnCadastrar);
    }

    private void buttonCadastrarAdminActionPerformed(ActionEvent ev) {
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

        dao.criarUsuario(new Usuario().setNomeUsuario(usuario).setSenha(senha).setIsAdmin(true));
        JOptionPane.showMessageDialog(this, "✅ Usuário administrativo cadastrado com sucesso!");
        txtUsuario.setText("");
        txtSenha.setText("");
        chkAdmin.setSelected(false);
        txtUsuario.requestFocus();
    }
}