package dao;
 
import java.util.ArrayList;
 
public class ProdutoDAO {
 
    private static final ProdutoDAO instance = new ProdutoDAO();
    public static ProdutoDAO getInstance() {
        return instance;
    }
 
    private final ArrayList<Produto> produtos = new ArrayList<>();
 
    public void cadastrarProduto(Produto p) {
        produtos.add(p);
    }
 
    public ArrayList<Produto> listar() {
        return produtos;
    }
 
    public void remover(int index) {
        produtos.remove(index);
    }
 
    public static class Produto {
 
        private String nome;
        private int quantidade;
 
        public Produto(String n, int q) {
            this.nome = n;
            this.quantidade = q;
        }
 
        public String getNome() { return nome; }
        public int getQuantidade() { return quantidade; }
    }
}