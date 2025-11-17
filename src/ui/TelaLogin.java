package ui;
 
import dao.UsuarioDAO;
import model.Usuario;
import utils.KonamiListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import ui.TelaCadastroUsuario;

 
public class TelaLogin extends JFrame {
 
    private final JTextField txtUsuario;
    private final JPasswordField txtSenha;
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
 
    private boolean senhaVisivel = false; // controle da visibilidade
 
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
        txtSenha.setBounds(110, 70, 150, 25);
        txtSenha.setEchoChar('*');
        add(txtSenha);
 
        // 🔵 BOTÃO DE MOSTRAR/OCULTAR SENHA
        JButton btVerSenha = new JButton("👁");
        btVerSenha.setBounds(265, 70, 30, 25);
        btVerSenha.addActionListener(e -> toggleSenha(btVerSenha));
        add(btVerSenha);
 
        JButton login = new JButton("Entrar");
        getRootPane().setDefaultButton(login);
        login.setBounds(110, 120, 100, 30);
        login.addActionListener(this::loginAction);
        add(login);

        JButton cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(220, 120, 100, 30);
        cadastrar.addActionListener(e -> {
            TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
            telaCadastro.setVisible(true);
        });
        add(cadastrar);
    }
 
    private void toggleSenha(JButton botao) {
        if (senhaVisivel) {
            txtSenha.setEchoChar('*');
            botao.setText("👁");
        } else {
            txtSenha.setEchoChar((char) 0); // mostra texto real
            botao.setText("🚫");
        }
        senhaVisivel = !senhaVisivel;
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