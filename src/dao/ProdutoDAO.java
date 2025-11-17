package dao;

import model.Produto;
import java.util.ArrayList;

public class ProdutoDAO {

    private static final ArrayList<Produto> produtos = new ArrayList<>();

    public void salvar(Produto p) {
        produtos.add(p);
        System.out.println("Produto salvo: " + p.getNome());
    }

    public ArrayList<Produto> getAll() {
        return produtos;
    }
}