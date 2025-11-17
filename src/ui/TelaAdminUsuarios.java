package ui;

import dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class TelaAdminUsuarios extends JFrame {

    private final UsuarioDAO dao = new UsuarioDAO();

    public TelaAdminUsuarios() {
        setTitle("Administração de Usuários");
        setSize(450, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Painel superior com botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            new TelaEstoque().setVisible(true);
            dispose();
        });
        
        JButton btnCadastrarUsuario = new JButton("Cadastrar Usuário");
        btnCadastrarUsuario.addActionListener(e -> {
            new TelaCadastroUsuario().setVisible(true);
        });
        
        panelBotoes.add(btnVoltar);
        panelBotoes.add(btnCadastrarUsuario);
        
        // Lista de usuários
        DefaultListModel<String> model = new DefaultListModel<>();
        dao.getAll().forEach(u ->
                model.addElement(u.getUsuario() + (u.isAdmin() ? " (admin)" : ""))
        );
        
        JList<String> lista = new JList<>(model);
        
        // Adicionar componentes
        add(panelBotoes, BorderLayout.NORTH);
        add(new JScrollPane(lista), BorderLayout.CENTER);
        
        setVisible(true);
    }
}