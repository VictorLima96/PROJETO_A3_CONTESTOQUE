package model;
 
public class Usuario {
    private int id;
    private final String usuario;
    private final boolean isAdmin;
 
        public Usuario(String u, String s, String senha, boolean admin) {
            this.usuario = u;
            this.isAdmin = admin;
        }

    public String getUsuario() {
        return this.usuario;
    }

    public int getId() {
        return this.id;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public static String getSenha() {
        return "12345";
    }

    }