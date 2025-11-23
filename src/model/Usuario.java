package model;

public class Usuario {
    private String id;
    private String nomeUsuario;
    private String senha;
    private String email;
    
    private boolean isAdmin = false;

    public String getId() {
        return this.id;
    }
    public Usuario setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }
    public Usuario setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public Usuario setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        return this;
    }

    public String getSenha() {
        return senha;
    }
    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }
    public Usuario setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }
}
