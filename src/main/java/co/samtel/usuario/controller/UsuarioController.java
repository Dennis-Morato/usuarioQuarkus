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
        LOG.info("Inicia proceso de crear usuario controller");
        UsuarioTypeResponse usuarioType = null;
        try{
         usuarioType =  usuarioServiceImpl.crearUsuario(usuarioTypeInput);
        }catch (ApplicationException e){
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + "Crear usuario controller");
            return  Response.status(Response.Status.BAD_REQUEST).entity(usuarioType).build();
        }
        LOG.info("Finaliza proceso de crear usuario controller");
        return Response.status(Response.Status.CREATED).entity(usuarioType).build();
    }
    @Override
    public Response eliminarUsuario(Integer idtblUser) {
        LOG.info("Inicia proceso de eliminar usuario Controller");
        UsuarioTypeResponse usuarioTypeResponse = null;
        try {
            usuarioServiceImpl.eliminarUsuario(idtblUser);
        } catch (ApplicationException e) {
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + "Eliminar usuario Controller");
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        LOG.info("Finaliza proceso de  eliminar usuario Controller");
        return Response.status(Response.Status.OK).entity(usuarioTypeResponse).build();
    }

    @Override
    public Response editarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Se inicializa proceso de editar usuario controller");
        UsuarioTypeResponse usuarioTypeResponse = null;
        try{
            usuarioTypeResponse = usuarioServiceImpl.editarUsuario(idtblUser , usuarioTypeInput);
        }catch (ApplicationException e){
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + " Editar usuario controller");
            return  Response.status(Response.Status.BAD_REQUEST).entity(usuarioTypeResponse).build();
        }
        LOG.info("Finaliza proceso de editar usuario controller");
        return Response.status(Response.Status.OK).entity(usuarioTypeResponse).build();
    }
    @Override
    public Response bucarUsuarioPorId(Integer idtblUser) {
        LOG.info("Inicia proceso de buscar al usuario por ID controller");
        UsuarioTypeResponse usuarioTypeResponse = null;
        try {
            usuarioTypeResponse = usuarioServiceImpl.bucarUsuarioPorId(idtblUser);
        }catch (ApplicationException e){
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + "Buscar al usuario por ID controller");
            return Response.status(Response.Status.BAD_REQUEST).entity(usuarioTypeResponse).build();
        }
        LOG.info("Finaliza proceso de editar usuario por ID controller");
        return Response.status(Response.Status.OK).entity(usuarioTypeResponse).build();
    }
    @Override
    public Response listarTodosLosUsuario() {
        LOG.info("Inicia proceso de listar todos los usuario Controller");
        List<UsuarioTypeResponse> response = null;
        try{
            response = usuarioServiceImpl.listarTodosLosUsuario();
        }catch (ApplicationException e){
            LOG.error(Constant.ERROR_SERVICIO + e.getMessage() + "Listar todos los usuario Controller");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        LOG.info("Finaliza el metodo listarTodosLosUsuario Controller");
        return Response.status(Response.Status.OK).entity(response).build();
    }
}
