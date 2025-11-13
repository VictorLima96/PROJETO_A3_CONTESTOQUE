package model;
 
public class Produto {
    private int id;
    private String nome;
    private int quantidade;
 
    public Produto(String nome, int qtd) {
        this.nome = nome;
        this.quantidade = qtd;
    }
 
    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }
}