package co.samtel.usuario.controller;

import co.samtel.usuario.gen.contract.V1UsuarioApi;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;
import co.samtel.usuario.service.impl.UsuarioServiceImpl;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class UsuarioController implements V1UsuarioApi {
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);

    @Inject
    UsuarioServiceImpl usuarioServiceImpl;

    @Override
    public List<UsuarioTypeResponse> crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Termino el proceso Crear ususario");
        return usuarioServiceImpl.crearUsuario(usuarioTypeInput);
    }
    @Override
    public void eliminarUsuario(Integer idtblUser) {
        LOG.info("Termina proceso de eliminar usuario");
        usuarioServiceImpl.eliminarUsuario(idtblUser);
    }

    @Override
    public List<UsuarioTypeResponse> editarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        return usuarioServiceImpl.editarUsuario(idtblUser , usuarioTypeInput);
    }
    @Override
    public List<UsuarioTypeResponse> listarUsuario(Integer idtblUser) {
        LOG.info("Termina Proceso de listar al usuario por ID");
        return usuarioServiceImpl.listarUsuario(idtblUser);
    }
    @Override
    public List<UsuarioTypeResponse> listarTodosLosUsuario() {
        return usuarioServiceImpl.listarTodosLosUsuario();
    }
}