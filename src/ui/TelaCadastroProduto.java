package ui;
 
import dao.ProdutoDAO;
import dao.ProdutoDAO.Produto;
 
import javax.swing.*;
 
public class TelaCadastroProduto extends JFrame {
 
    public TelaCadastroProduto(TelaEstoque estoque) {
        setTitle("Cadastro de Produto");
        setSize(300, 200);
        setLayout(null);
        setLocationRelativeTo(null);
 
        JLabel ln = new JLabel("Nome:");
        ln.setBounds(20, 20, 80, 25);
        add(ln);
 
        JTextField nome = new JTextField();
        nome.setBounds(100, 20, 150, 25);
        add(nome);
 
        JLabel lq = new JLabel("Quantidade:");
        lq.setBounds(20, 60, 80, 25);
        add(lq);
 
        JTextField qtd = new JTextField();
        qtd.setBounds(100, 60, 150, 25);
        add(qtd);
 
        JButton salvar = new JButton("Salvar");
        salvar.setBounds(80, 110, 120, 30);
        salvar.addActionListener(e -> {
            String n = nome.getText();
            int q = Integer.parseInt(qtd.getText());
 
            ProdutoDAO.getInstance().cadastrarProduto(new Produto(n, q));
 
            estoque.atualizarTabela();
            dispose();
        });
 
        add(salvar);
    }
}