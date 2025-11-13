package ui;
 
import dao.UsuarioDAO;
import model.Usuario;
import utils.KonamiListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
 
public class TelaLogin extends JFrame {
 
    private final JTextField txtUsuario;
    private final JPasswordField txtSenha;
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
 
    public TelaLogin() {
        setTitle("Login do Sistema");
        setSize(350, 230);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        addKeyListener(new KonamiListener(this.getContentPane()));
        setFocusable(true);
 
        JLabel l1 = new JLabel("Usuário:");
        l1.setBounds(30, 30, 80, 25);
        add(l1);
 
        txtUsuario = new JTextField();
        txtUsuario.setBounds(110, 30, 180, 25);
        add(txtUsuario);
 
        JLabel l2 = new JLabel("Senha:");
        l2.setBounds(30, 70, 80, 25);
        add(l2);
 
        txtSenha = new JPasswordField();
        txtSenha.setBounds(110, 70, 180, 25);
        add(txtSenha);
 
        JButton login = new JButton("Entrar");
        login.setBounds(110, 120, 100, 30);
        login.addActionListener(this::loginAction);
        add(login);
    }
 
    private void loginAction(ActionEvent evt) {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());
 
        Usuario u = usuarioDAO.login(usuario, senha);
 
        if (u != null) {
            JOptionPane.showMessageDialog(this, "Bem-vindo, " + usuario + "!");
            if (u.isAdmin()) {
                new TelaAdminUsuarios().setVisible(true);
            } else {
                new TelaEstoque().setVisible(true);
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!");
        }
    }
}