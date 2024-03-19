package co.samtel.usuario.controller;

import co.samtel.usuario.constant.Constant;
import co.samtel.usuario.gen.contract.V1UsuarioApi;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;
import co.samtel.usuario.service.impl.UsuarioServiceImpl;
import co.samtel.usuario.utils.exception.ApplicationException;
import com.mysql.cj.log.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
public class UsuarioController implements V1UsuarioApi {
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);

    @Inject
    UsuarioServiceImpl usuarioServiceImpl;

    @Override
    public Response crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Se inicia crear usuario controller");
        UsuarioTypeResponse usuarioType = null;
        try{
         usuarioType =  usuarioServiceImpl.crearUsuario(usuarioTypeInput);
        }catch (ApplicationException e){
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + "C");
            return  Response.status(Response.Status.BAD_REQUEST).entity(usuarioType).build();
        }
        LOG.info("Finaliza crear usuario controller");
        return Response.status(Response.Status.CREATED).entity(usuarioType).build();
    }
    @Override
    public Response eliminarUsuario(Integer idtblUser) {
        LOG.info("Termina proceso de eliminar usuario");
        usuarioServiceImpl.eliminarUsuario(idtblUser);
    }

    @Override
    public Response editarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Se inicializa proceso de editar usuario controller");
        UsuarioTypeResponse usuarioTypeResponse = null;
        try{
            usuarioTypeResponse = usuarioServiceImpl.editarUsuario(idtblUser , usuarioTypeInput);
        }catch (ApplicationException e){
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + "C");
            return  Response.status(Response.Status.BAD_REQUEST).entity(usuarioTypeResponse).build();
        }
        LOG.info("Finaliza proceso de editar usuario controller");
        return Response.status(Response.Status.OK).entity(usuarioTypeResponse).build();
    }
    @Override
    public Response listarUsuario(Integer idtblUser) {
        LOG.info("inicia proceso de listar al usuario por ID controller");
        UsuarioTypeResponse usuarioTypeResponse = null;
        try {
            usuarioTypeResponse = usuarioServiceImpl.listarUsuario(idtblUser);
        }catch (ApplicationException e){
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(usuarioTypeResponse).build();
        }
        LOG.info("Se finaliza proceso de editar usuario controller");
        return Response.status(Response.Status.OK).entity(usuarioTypeResponse).build();
    }
    @Override
    public Response listarTodosLosUsuario() {
        return usuarioServiceImpl.listarTodosLosUsuario();
    }
}