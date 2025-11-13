package ui;
 
import dao.ProdutoDAO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
 
public final class TelaEstoque extends JFrame {
 
    private final ProdutoDAO dao = new ProdutoDAO();
    private final JTable tabela = new JTable();
 
    public TelaEstoque() {
        setTitle("Controle de Estoque");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        JButton novo = new JButton("Novo Produto");
        novo.addActionListener(e -> new TelaCadastroProduto(this).setVisible(true));
 
        atualizarTabela();
 
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(novo, BorderLayout.SOUTH);
    }
 
    void atualizarTabela() {
        ArrayList<dao.ProdutoDAO.Produto> lista = dao.listar();
 
        String[][] dados = new String[lista.size()][2];
 
        for (int i = 0; i < lista.size(); i++) {
            dados[i][0] = lista.get(i).getNome();
            dados[i][1] = String.valueOf(lista.get(i).getQuantidade());
        }
 
        tabela.setModel(new javax.swing.table.DefaultTableModel(
                dados,
                new String[]{"Nome", "Quantidade"}
        ));
    }
}