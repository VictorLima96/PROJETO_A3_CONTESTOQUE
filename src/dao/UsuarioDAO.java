package dao;

import model.Usuario;
import java.util.ArrayList;

public class UsuarioDAO {

    private static final ArrayList<Usuario> fakeDB = new ArrayList<>();

    static {
        fakeDB.add(new Usuario("admin", "12345", true)); // usuário padrão
    }

    public Usuario login(String usuario, String senha) {
        return fakeDB.stream()
                .filter(u -> u.getUsuario().equals(usuario) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }

    public static void salvar(Usuario usuario) {
        fakeDB.add(usuario);
        System.out.println("Usuário salvo: " + usuario.getUsuario());
    }

    public ArrayList<Usuario> getAll() {
        return fakeDB;
    }

    

}