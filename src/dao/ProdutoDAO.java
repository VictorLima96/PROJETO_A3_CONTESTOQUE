package dao;
 
import java.util.ArrayList;
 
public class ProdutoDAO {
 
    private static final ArrayList<Produto> produtos = new ArrayList<>();
 
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

        public Produto() {
        }

        public Produto(String n, int q) {
            
        }

        public String getNome() {
            
            throw new UnsupportedOperationException("Unimplemented method 'getNome'");
        }

        public char[] getQuantidade() {
            
            throw new UnsupportedOperationException("Unimplemented method 'getQuantidade'");
        }
    }
}