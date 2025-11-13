package dao;
 
import model.Usuario;
import java.util.ArrayList;
 
public class UsuarioDAO {
    private static final ArrayList<Usuario> fakeDB = new ArrayList<>();
 
    static {
        fakeDB.add(new Usuario("admin", "12345", true));
        fakeDB.add(new Usuario("kaique", "senha", false));
    }
 
    public Usuario login(String usuario, String senha) {
        return fakeDB.stream()
                .filter(u -> u.getUsuario().equals(usuario) && u.getSenha().equals(senha))
                .findFirst()
                .orElse(null);
    }
 
    public void cadastrar(Usuario u) {
        fakeDB.add(u);
    }
 
    public ArrayList<Usuario> getAll() {
        return fakeDB;
    }
}