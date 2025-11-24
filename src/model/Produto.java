package model;

public class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private String status; 
    private String codProduto;
    private int codUsuario; 

    public int getId() {
        return id;
    }
    public Produto setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }
    public Produto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public int getQuantidade() {
        if(this.status == "Inativo") {
            return 0;
        }

        return quantidade;
    }
    public Produto setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public String getCodProduto() {
        return codProduto;
    }
    public Produto setCodProduto(String codProduto) {
        this.codProduto = codProduto;
        return this;
    }

    public int getCodUsuario() {
        return codUsuario;
    }
    public Produto setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
        return this;
    }

    public String getStatus() {
        return status;         
    }
    public Produto setStatus(String status) {
        this.status = status;
        return this;
    }
}