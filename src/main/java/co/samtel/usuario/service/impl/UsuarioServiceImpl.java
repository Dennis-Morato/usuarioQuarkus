package co.samtel.usuario.service.impl;

import co.samtel.usuario.dao.UsuarioDao;
import co.samtel.usuario.entity.Usuario;
import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;
import co.samtel.usuario.utils.ApplicationException;
import co.samtel.usuario.utils.UsuarioMapper;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static co.samtel.usuario.constant.Constant.ERROR_SERVICIO;



@ApplicationScoped
public class UsuarioServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioTypeResponse.class);

    @Inject
    UsuarioMapper usuarioMapper;

    @Inject
    UsuarioDao usuarioDao;

    @Transactional
    public List<UsuarioTypeResponse> crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Inicia crearUsuarioImpl");
        try {
            Usuario usuario = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);
            usuarioDao.persist(usuario);
            UsuarioTypeResponse response = usuarioMapper.usuarioEntityToType(usuario);
            LOG.info("Persis usuario");
            return Collections.singletonList(response);
        }catch (ApplicationException e){
            LOG.error("Error al crear usuario");
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public void eliminarUsuario(Integer idtblUser){
        LOG.info("Se inicia proceso de eliminar usuario");
        try {
            Long idUsuario = Long.valueOf(idtblUser);
            usuarioDao.deleteById(idUsuario);
            LOG.info("Se termina proceso de eliminar usuario ");
        }catch (ApplicationException e){
            LOG.error("Se presento el siguiente error al eliminar el usuario " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public List<UsuarioTypeResponse> editarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Se inicia proceso de editar usuario");
        try {
            Long idUsuario = Long.valueOf(idtblUser);
            Usuario usuario = usuarioDao.findById(idUsuario);
            Usuario usuarioEditado = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);

            usuario.setName(usuarioEditado.getName());
            usuario.setLastname(usuarioEditado.getLastname());

            UsuarioTypeResponse usuarioTypeResponse = usuarioMapper.usuarioEntityToType(usuarioEditado);
            LOG.info("Se finaliza proceso de editar usuario");
            return Collections.singletonList(usuarioTypeResponse);
        }catch (ApplicationException e){
            LOG.error("Se presento el siguiente error editado el usuario " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public List<UsuarioTypeResponse> listarUsuario(Integer idtblUser) {
        LOG.info("Se inicia proceso de listar usuario");
        try{
            Long idUsuario = Long.valueOf(idtblUser);
            Usuario usuario = usuarioDao.findById(idUsuario);
            UsuarioTypeResponse usuarioTypeResponse = usuarioMapper.usuarioEntityToType(usuario);
            LOG.info("Se termina proceso de listar usuario");
            return Collections.singletonList(usuarioTypeResponse);
        }catch (ApplicationException e){
            LOG.error("Se presento el siguiente error listando al usuario " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public List<UsuarioTypeResponse> listarTodosLosUsuario() {
        LOG.info("Inicia el listado de todos los usuarios");
        try{
            PanacheQuery listQuery = usuarioDao.findAll();
            List<Usuario> usuario = listQuery.list();
            return usuarioMapper.usuariosTypeListEntityToTypeResponse(usuario);
        }catch(ApplicationException e){
            LOG.error("Se presento un error al listar todos los usuario"+ e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }
}