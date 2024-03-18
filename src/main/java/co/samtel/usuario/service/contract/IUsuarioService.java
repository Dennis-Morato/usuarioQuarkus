package co.samtel.usuario.service.contract;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;

public interface IUsuarioService {
    UsuarioTypeInput crearUsuario(UsuarioTypeResponse usuarioTypeInput);
}