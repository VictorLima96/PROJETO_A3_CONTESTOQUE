package model;

public class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private String tipo;
    private double altura;
    private double largura;
    private double profundidade;
    private String codRastreio;
    private int codUsuario;
    private String status; 

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

    public String getTipo() {
        return tipo;
    }
    public Produto setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public double getAltura() {
        return altura;
    }
    public Produto setAltura(double altura) {
        this.altura = altura;
        return this;
    }

    public double getLargura() {
        return largura;
    }
    public Produto setLargura(double largura) {
        this.largura = largura;
        return this;
    }

    public double getProfundidade() {
        return profundidade;
    }
    public Produto setProfundidade(double profundidade) {
        this.profundidade = profundidade;
        return this;
    }

    public String getCodRastreio() {
        return codRastreio;
    }
    public Produto setCodRastreio(String codRastreio) {
        this.codRastreio = codRastreio;
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