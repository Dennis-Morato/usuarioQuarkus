package co.samtel.usuario.service.contract;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;

import java.util.List;

public interface IUsuarioService {
    public UsuarioTypeResponse crearUsuario(UsuarioTypeInput usuarioTypeInput);
    public void eliminarUsuario(Integer idtblUser);
    public UsuarioTypeResponse editarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput);
    public UsuarioTypeResponse bucarUsuarioPorId(Integer idtblUser);
    public List<UsuarioTypeResponse> listarTodosLosUsuario();
}