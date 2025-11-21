package model;

import java.util.UUID;

public class Usuario {
    private UUID codUsuario = UUID.randomUUID();
    private String nomeUsuario;
    private String senha;
    private String email;
    
    private boolean isAdmin = false;

    public UUID getCodUsuario() {
        return codUsuario;
    }
    public Usuario setCodUsuario(UUID codUsuario) {
        this.codUsuario = codUsuario;
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
