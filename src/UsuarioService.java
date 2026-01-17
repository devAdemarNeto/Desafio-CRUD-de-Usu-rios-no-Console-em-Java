import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private List<Usuario> usuarios;
    private int contadorId;

    public UsuarioService() {
        this.usuarios = new ArrayList<>();
        this.contadorId = 1;
    }



    public boolean cadastrarUsuario(String nome, String email){
        if (!nomeValido(nome) || !emailValido(email)){
            return false;
        }

        Usuario usuario = new Usuario(contadorId, nome.trim(), email.trim());
        usuarios.add(usuario);
        contadorId++;
        return true;
    }

    public List<Usuario> listarUsuarios(){
        return new ArrayList<>(usuarios);
    }


    public Usuario buscarPorId(int id){
        for(Usuario usuario : usuarios){
            if (usuario.getId() == id){
                return usuario;
            }
        }
        return null;
    }


    public boolean atualizarUsuario(int id, String novoNome, String novoEmail){
        Usuario usuario = buscarPorId(id);
        if (usuario == null){
            return false;
        }

        // Atualiza nome apenas se foi informado
        if (novoNome != null && !novoNome.trim().isEmpty()) {
            usuario.setNome(novoNome.trim());
        }

        // Atualiza email apenas se foi informado
        if (novoEmail != null && !novoEmail.trim().isEmpty()) {
            if (!emailValido(novoEmail)) {
                return false; // email inválido
            }
            usuario.setEmail(novoEmail.trim());
        }

        return true;
    }


    public boolean removerUsuario(int id){
        Usuario usuario = buscarPorId(id);
        if (usuario == null){
            return false;
        }

        usuarios.remove(usuario);
        return true;
    }




    // Validações

    private boolean nomeValido(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    private boolean emailValido(String email) {
        return email != null && !email.trim().isEmpty() && email.contains("@");
    }


}
