package ui;

import dao.UsuarioDAO;
import java.awt.*;
import javax.swing.*;

public class TelaAdminUsuarios extends JFrame {
    private final UsuarioDAO dao = new UsuarioDAO();

    public TelaAdminUsuarios() {
        super("Administração de Usuários");
        setSize(450, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelBotoes = new JPanel(new FlowLayout());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            new TelaEstoque().setVisible(true);
            dispose();
        });
        panelBotoes.add(btnVoltar);

        JButton btnCadastrar = new JButton("Cadastrar Usuário");
        btnCadastrar.addActionListener(e -> new TelaCadastroUsuario().setVisible(true));
        panelBotoes.add(btnCadastrar);

        DefaultListModel<String> model = new DefaultListModel<>();
        dao.obterTodos().forEach(u -> model.addElement(u.getCodUsuario() + (u.getIsAdmin() ? " (admin)" : "")));
        JList<String> lista = new JList<>(model);

        add(panelBotoes, BorderLayout.NORTH);
        add(new JScrollPane(lista), BorderLayout.CENTER);
        setVisible(true);
    }
}