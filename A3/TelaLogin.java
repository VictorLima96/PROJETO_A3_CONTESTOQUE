import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class TelaLogin extends JFrame {
    private final JTextField txtUsuario;
    private final JPasswordField pwdSenha;
    private final JButton btnEntrar, btnCadastrar;
    private static final Map<String, String> usuarios = new HashMap<>();

    public TelaLogin() {
        // Usuário administrador padrão
        usuarios.put("admin", "12345");

        setTitle("Tela de Login");
        setSize(400, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(50, 50, 100, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 50, 180, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 90, 100, 25);
        add(lblSenha);

        pwdSenha = new JPasswordField();
        pwdSenha.setBounds(150, 90, 180, 25);
        add(pwdSenha);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(70, 150, 120, 35);
        btnEntrar.addActionListener(this::btnEntrarActionPerformed);
        add(btnEntrar);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(210, 150, 120, 35);
        btnCadastrar.addActionListener(e -> {
            new TelaCadastro(usuarios).setVisible(true);
            this.dispose();
        });
        add(btnCadastrar);
    }

    private void btnEntrarActionPerformed(ActionEvent evt) {
        String usuario = txtUsuario.getText();
        String senha = new String(pwdSenha.getPassword());

        if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            if (usuario.equals("admin")) {
                new TelaAdmin(usuarios).setVisible(true);
            } else {
                new Teste().setVisible(true);
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaLogin().setVisible(true));
    }
}

/* ------------------------- Tela de Cadastro ------------------------- */
class TelaCadastro extends JFrame {
    private final JTextField txtNovoUsuario;
    private final JPasswordField pwdNovaSenha;
    private final JButton btnSalvar, btnVoltar;
    private final Map<String, String> usuarios;

    public TelaCadastro(Map<String, String> usuarios) {
        this.usuarios = usuarios;

        setTitle("Cadastro de Usuário");
        setSize(400, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblUsuario = new JLabel("Novo Usuário:");
        lblUsuario.setBounds(50, 50, 100, 25);
        add(lblUsuario);

        txtNovoUsuario = new JTextField();
        txtNovoUsuario.setBounds(150, 50, 180, 25);
        add(txtNovoUsuario);

        JLabel lblSenha = new JLabel("Nova Senha:");
        lblSenha.setBounds(50, 90, 100, 25);
        add(lblSenha);

        pwdNovaSenha = new JPasswordField();
        pwdNovaSenha.setBounds(150, 90, 180, 25);
        add(pwdNovaSenha);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(70, 150, 120, 35);
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);
        add(btnSalvar);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(210, 150, 120, 35);
        btnVoltar.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            this.dispose();
        });
        add(btnVoltar);
    }

    private void btnSalvarActionPerformed(ActionEvent evt) {
        String novoUsuario = txtNovoUsuario.getText();
        String novaSenha = new String(pwdNovaSenha.getPassword());

        if (novoUsuario.isEmpty() || novaSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        if (usuarios.containsKey(novoUsuario)) {
            JOptionPane.showMessageDialog(this, "Usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarios.put(novoUsuario, novaSenha);
        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        new TelaLogin().setVisible(true);
        this.dispose();
    }
}

/* ------------------------- Tela de Administração ------------------------- */
class TelaAdmin extends JFrame {
    private final Map<String, String> usuarios;
    private final DefaultListModel<String> modeloLista;
    private final JList<String> listaUsuarios;

    public TelaAdmin(Map<String, String> usuarios) {
        this.usuarios = usuarios;

        setTitle("Painel de Administração");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel lblTitulo = new JLabel("Usuários Cadastrados:");
        lblTitulo.setBounds(20, 20, 200, 25);
        add(lblTitulo);

        modeloLista = new DefaultListModel<>();
        atualizarLista();

        listaUsuarios = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaUsuarios);
        scroll.setBounds(20, 50, 340, 200);
        add(scroll);

        JButton btnExcluir = new JButton("Excluir Usuário");
        btnExcluir.setBounds(20, 270, 150, 30);
        btnExcluir.addActionListener(this::excluirUsuario);
        add(btnExcluir);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(210, 270, 150, 30);
        btnSair.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            this.dispose();
        });
        add(btnSair);
    }

    private void atualizarLista() {
        modeloLista.clear();
        for (String user : usuarios.keySet()) {
            modeloLista.addElement(user);
        }
    }

    private void excluirUsuario(ActionEvent e) {
        String selecionado = listaUsuarios.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário!");
            return;
        }
        if (selecionado.equals("admin")) {
            JOptionPane.showMessageDialog(this, "O administrador não pode ser excluído!");
            return;
        }

        usuarios.remove(selecionado);
        JOptionPane.showMessageDialog(this, "Usuário excluído!");
        atualizarLista();
    }
}

/* ------------------------- Telas Decorativas ------------------------- */
class Teste extends JFrame {
    ImageIcon imagem = new ImageIcon(getClass().getResource("image-removebg-preview.png"));
    JLabel label = new JLabel(imagem);

    public Teste() {
        setTitle("POLLO ALERT");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("image-removebg-preview.png")));

        add(label);
        label.setBounds(300, 50, imagem.getIconWidth(), imagem.getIconHeight());

        JButton botao = new JButton("Sair");
        botao.setBounds(250, 350, 250, 70);
        botao.setFont(new Font("Arial", Font.BOLD, 40));
        botao.setForeground(new Color(16, 22, 193));
        botao.setBackground(new Color(6, 2, 19));
        botao.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            this.dispose();
        });
        add(botao);
    }
}