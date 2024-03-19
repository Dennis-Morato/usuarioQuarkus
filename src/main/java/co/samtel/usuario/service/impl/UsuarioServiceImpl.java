package co.samtel.usuario.service.impl;

import co.samtel.usuario.dao.UsuarioDao;
import co.samtel.usuario.entity.Usuario;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;
import co.samtel.usuario.service.contract.IUsuarioService;
import co.samtel.usuario.utils.exception.ApplicationException;
import co.samtel.usuario.utils.mapper.UsuarioMapper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static co.samtel.usuario.constant.Constant.ERROR_SERVICIO;



@ApplicationScoped
public class UsuarioServiceImpl implements IUsuarioService {
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Inject
    UsuarioMapper usuarioMapper;

    @Inject
    UsuarioDao usuarioDao;

    @Transactional
    public UsuarioTypeResponse crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Inicia crearUsuarioImpl");
        try {
            Usuario usuario = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);
            usuarioDao.persist(usuario);
            UsuarioTypeResponse response = usuarioMapper.usuarioEntityToType(usuario);
            LOG.info("Persis usuario");
            return response;
        }catch (ApplicationException e){
            LOG.error("Error al crear usuario");
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public UsuarioTypeResponse eliminarUsuario(Integer idtblUser){
        LOG.info("Se inicia proceso de eliminar usuario impl");
        try {
            Long idUsuario = Long.valueOf(idtblUser);
            usuarioDao.deleteById(idUsuario);
            return
            LOG.info("Se termina proceso de eliminar usuario impl");
        }catch (ApplicationException e){
            LOG.error("Se presento el siguiente error al eliminar el usuario impl " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public UsuarioTypeResponse editarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Se inicia proceso de editar usuario impl");
        try {
            Long idUsuario = Long.valueOf(idtblUser);
            Usuario usuario = usuarioDao.findById(idUsuario);
            Usuario usuarioActualizado = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);

            usuario.setName(usuarioActualizado.getName());
            usuario.setLastname(usuarioActualizado.getLastname());

            UsuarioTypeResponse usuarioTypeResponse = usuarioMapper.usuarioEntityToType(usuarioActualizado);
            LOG.info("Se finaliza proceso de editar usuario impl");
            return usuarioTypeResponse;
        }catch (ApplicationException e){
            LOG.error("Se presento el siguiente error editado el usuario impl" + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    public UsuarioTypeResponse bucarUsuarioPorId(Integer idtblUser) {
        LOG.info("Se inicia proceso de listar usuario por id impl");
        try{
            Long idUsuario = Long.valueOf(idtblUser);
            Usuario usuario = usuarioDao.findById(idUsuario);
            UsuarioTypeResponse usuarioTypeResponse = usuarioMapper.usuarioEntityToType(usuario);
            LOG.info("Se termina proceso de listar usuario por id impl");
            return usuarioTypeResponse;
        }catch (ApplicationException e){
            LOG.error("Se presento el siguiente error listando al usuario por id impl " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    public UsuarioTypeResponse listarTodosLosUsuario() {
        LOG.info("Inicia el listado de todos los usuarios");
        try{
            PanacheQuery listQuery = usuarioDao.findAll();
            List<Usuario> usuario = listQuery.list();
            UsuarioTypeResponse usuarioTypeResponse = usuarioMapper.usuariosTypeListEntityToTypeResponse(usuario);
            return usuarioTypeResponse;
        }catch(ApplicationException e){
            LOG.error("Se presento un error al listar todos los usuario"+ e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }
}