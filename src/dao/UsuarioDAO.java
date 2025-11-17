package dao;
 
import model.Usuario;
import java.util.ArrayList;
 
public class UsuarioDAO {
    private static final ArrayList<Usuario> fakeDB = new ArrayList<>();
    private static String senha = "12345";
 
    static {
        fakeDB.add(new Usuario("admin", "12345", senha, true));
        fakeDB.add(new Usuario("kaique", "senha", senha, false));
    }

    public static void salvar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static String getSenha() {
        return senha;
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