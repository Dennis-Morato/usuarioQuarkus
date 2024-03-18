package co.samtel.usuario.gen.contract;

import co.samtel.usuario.gen.type.UsuarioTypeInput;
import co.samtel.usuario.gen.type.UsuarioTypeResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;




import java.io.InputStream;
import java.util.Map;
import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;


@Path("/v1/es")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2024-03-18T08:02:35.783800800-05:00[America/Bogota]", comments = "Generator version: 7.4.0")
public interface V1UsuarioApi {

    @POST
    @Path("/crearUsuario")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    List<UsuarioTypeResponse> crearUsuario(@Valid UsuarioTypeInput usuarioTypeInput);

    @PUT
    @Path("/editarUsuario/{idtbl_user}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    List<UsuarioTypeResponse> editarUsuario(@PathParam("idtbl_user") @Min(1) Integer idtblUser,@Valid UsuarioTypeInput usuarioTypeInput);

    @DELETE
    @Path("/eliminarUsuario/{idtbl_user}")
    void eliminarUsuario(@PathParam("idtbl_user") Integer idtblUser);

    @GET
    @Path("/listarUsuarios")
    @Produces({ "application/json" })
    List<UsuarioTypeResponse> listarTodosLosUsuario();

    @GET
    @Path("/listarUsuario/{idtbl_user}")
    @Produces({ "application/json" })
    List<UsuarioTypeResponse> listarUsuario(@PathParam("idtbl_user") Integer idtblUser);
}
