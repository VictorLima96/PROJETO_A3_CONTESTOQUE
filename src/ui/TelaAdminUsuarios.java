package ui;
 
import dao.UsuarioDAO;
 
import javax.swing.*;
import java.awt.*;
 
public class TelaAdminUsuarios extends JFrame {
 
    private final UsuarioDAO dao = new UsuarioDAO();
 
    public TelaAdminUsuarios() {
        setTitle("Administração de Usuários");
        setSize(450, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        DefaultListModel<String> model = new DefaultListModel<>();
 
        dao.getAll().forEach(u ->
                model.addElement(u.getUsuario() + (u.isAdmin() ? " (admin)" : ""))
        );
 
        JList<String> lista = new JList<>(model);
 
        JButton abrirEstoque = new JButton("Ir para Estoque");
        abrirEstoque.addActionListener(e -> {
            new TelaEstoque().setVisible(true);
            this.dispose();
        });
 
        add(new JScrollPane(lista), BorderLayout.CENTER);
        add(abrirEstoque, BorderLayout.SOUTH);
    }
}