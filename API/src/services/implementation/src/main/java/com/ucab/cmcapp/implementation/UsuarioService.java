package com.ucab.cmcapp.implementation;

import com.ucab.cmcapp.common.entities.Usuario;
import com.ucab.cmcapp.common.util.CustomResponse;
import com.ucab.cmcapp.logic.commands.CommandFactory;
import com.ucab.cmcapp.logic.commands.usuario.atomic.GetUsuarioByCorreoCommand;
import com.ucab.cmcapp.logic.commands.usuario.atomic.GetUsuarioByAliasCommand;
import com.ucab.cmcapp.logic.commands.usuario.atomic.GetUsuarioByCedulaCommand;
import com.ucab.cmcapp.logic.commands.usuario.atomic.GetUsuarioByMacCommand;
import com.ucab.cmcapp.logic.commands.usuario.composite.*;
import com.ucab.cmcapp.logic.dtos.dtos.UsuarioDto;
import com.ucab.cmcapp.logic.dtos.extras.LDAPUsuarioDto;
import com.ucab.cmcapp.logic.mappers.UsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioService extends BaseService {
    private static Logger _logger = LoggerFactory.getLogger(UsuarioService.class);

    /**
     * Obtiene un usuario por su ID.
     *
     * @param usuarioId ID del usuario a obtener.
     * @return La respuesta HTTP con el usuario encontrado o un mensaje de error si no se encuentra.
     */
    @GET
    @Path("/{id}")
    public Response getUsuario(@PathParam("id") long usuarioId) {
        Usuario entity;
        UsuarioDto responseDTO = null;
        GetUsuarioCommand command = null;

        try {
            entity = UsuarioMapper.mapDtoToEntity(usuarioId);
            command = CommandFactory.createGetUsuarioCommand(entity);
            command.execute();

            if (command.getReturnParam() != null)
                responseDTO = UsuarioMapper.mapEntityToDto(command.getReturnParam());
            else
                return Response.status(Response.Status.OK).entity(new CustomResponse<>("El ID " + usuarioId + " de usuario no existe en la BBDD ")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CustomResponse<>(e, "Error interno en la ruta ID " + e.getMessage())).build();
        } finally {
            if (command != null)
                command.closeHandlerSession();
        }

        return Response.status(Response.Status.OK).entity(new CustomResponse<>(responseDTO, "El ID " + usuarioId + " del usuario ha sido encontrado correctamente")).build();
    }

    /**
     * Obtiene todos los usuarios.
     *
     * @return La respuesta HTTP con la lista de usuarios encontrados o un mensaje de error si no hay usuarios en la base de datos.
     */
    @GET
    @Path("/todos")
    public Response getAllUsuario() {
        List <UsuarioDto> responseDTO = null;
        GetAllUsuarioCommand command = null;

        try {
            command = CommandFactory.createGetAllUsuarioCommand();
            command.execute();
            responseDTO = UsuarioMapper.mapEntityListToDtoList(command.getReturnParam());

            if (responseDTO.size() == 0) {
                return Response.status(Response.Status.OK).entity(new CustomResponse<>("La base de datos esta vacia")).build();
            }

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CustomResponse<>(e, "Error interno al ejecutar la ruta todos: " + e.getMessage())).build();
        } finally {
            if (command != null)
                command.closeHandlerSession();
        }

        return Response.status(Response.Status.OK).entity(new CustomResponse<>(responseDTO, "Los usuarios se han obtenido correctamente")).build();
    }

    /**
     * Obtiene un usuario por su correo electrónico.
     *
     * @param correo Correo electrónico del usuario a obtener.
     * @return La respuesta HTTP con el usuario encontrado o un mensaje de error si no se encuentra.
     */
    @GET
    @Path("correo/{correo}")
    public Response getUsuarioByCorreo(@PathParam("correo") String correo) {
        Usuario entity;
        UsuarioDto responseDTO = null;
        GetUsuarioByCorreoCommand command = null;

        try {
            entity = UsuarioMapper.mapDtoToEntityCorreo(correo);
            command = CommandFactory.createGetUsuarioByCorreoCommand(entity);
            command.execute();

            if (command.getReturnParam() != null)
                responseDTO = UsuarioMapper.mapEntityToDto(command.getReturnParam());
            else
                return Response.status(Response.Status.OK).entity(new CustomResponse<>("El correo " + correo + " no ha sido encontrado en la BBDD")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CustomResponse<>("Error interno al obtener el correo: " + e.getMessage())).build();
        } finally {
            if (command != null)
                command.closeHandlerSession();
        }

        return Response.status(Response.Status.OK).entity(new CustomResponse<>(responseDTO, "EL correo " + correo + " del usuario ha sido encontrado exitosamente")).build();
    }

    /**
     * Obtiene un usuario por su alias.
     *
     * @param alias Alias del usuario a obtener.
     * @return La respuesta HTTP con el usuario encontrado o un mensaje de error si no se encuentra.
     */
    @GET
    @Path("alias/{alias}")
    public Response getUsuarioByAlias(@PathParam("alias") String alias) {
        Usuario entity;
        UsuarioDto responseDTO = null;
        GetUsuarioByAliasCommand command = null;

        try {
            entity = UsuarioMapper.mapDtoToEntityAlias(alias);
            command = CommandFactory.createGetUsuarioByAliasCommand(entity);
            command.execute();

            if (command.getReturnParam() != null)
                responseDTO = UsuarioMapper.mapEntityToDto(command.getReturnParam());
            else
                return Response.status(Response.Status.OK).entity(new CustomResponse<>("El usuario con el alias " + alias + " no existen en la BBDD")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CustomResponse<>("Error interno en la ruta alias: " + e.getMessage())).build();
        } finally {
            if (command != null)
                command.closeHandlerSession();
        }

        return Response.status(Response.Status.OK).entity(new CustomResponse<>(responseDTO, "EL usuario con el alias " + alias + " ha sido encontrado exitosamente")).build();
    }

    /**
     * Obtiene un usuario por su número de cédula.
     *
     * @param cedula Número de cédula del usuario a obtener.
     * @return La respuesta HTTP con el usuario encontrado o un mensaje de error si no se encuentra.
     */
    @GET
    @Path("cedula/{cedula}")
    public Response getUsuarioByCedula(@PathParam("cedula") String cedula) {
        Usuario entity;
        UsuarioDto responseDTO = null;
        GetUsuarioByCedulaCommand command = null;

        try {
            entity = UsuarioMapper.mapDtoToEntityCedula(cedula);
            command = CommandFactory.createGetUsuarioByCedulaCommand(entity);
            command.execute();

            if (command.getReturnParam() != null)
                responseDTO = UsuarioMapper.mapEntityToDto(command.getReturnParam());
            else
                return Response.status(Response.Status.OK).entity(new CustomResponse<>("El usuario con la cedula " + cedula + " no ha sido encontrada en la BBDD")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CustomResponse<>("Error interno en la ruta cedula: " + e.getMessage())).build();
        } finally {
            if (command != null)
                command.closeHandlerSession();
        }

        return Response.status(Response.Status.OK).entity(new CustomResponse<>(responseDTO, "El usuario con la cedula " + cedula + " ha sido encontrado exitosamente")).build();
    }

    /**
     * Obtiene un usuario por su dirección MAC.
     *
     * @param mac Dirección MAC del usuario a obtener.
     * @return La respuesta HTTP con el usuario encontrado o un mensaje de error si no se encuentra.
     */
    @GET
    @Path("mac/{mac}")
    public Response getUsuarioByMac(@PathParam("mac") String mac) {
        Usuario entity;
        UsuarioDto responseDTO = null;
        GetUsuarioByMacCommand command = null;

        try {
            entity = UsuarioMapper.mapDtoToEntityMac(mac);
            command = CommandFactory.createGetUsuarioByMacCommand(entity);
            command.execute();

            if (command.getReturnParam() != null)
                responseDTO = UsuarioMapper.mapEntityToDto(command.getReturnParam());
            else
                return Response.status(Response.Status.OK).entity(new CustomResponse<>("El usuario con la MAC " + mac + " no ha sido encontrada en la BBDD")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CustomResponse<>("Error interno en la ruta cedula: " + e.getMessage())).build();
        } finally {
            if (command != null)
                command.closeHandlerSession();
        }

        return Response.status(Response.Status.OK).entity(new CustomResponse<>(responseDTO, "El usuario con la MAC " + mac + " ha sido encontrado exitosamente")).build();
    }

    /**
     * Agrega un nuevo usuario.
     *
     * @param usuarioDto El objeto `UsuarioDto` que representa los datos del usuario a agregar.
     * @return La respuesta HTTP con el usuario agregado o un mensaje de error si no se puede agregar.
     */
    @POST
    public Response addUsuario(UsuarioDto usuarioDto) {
        Usuario entity;
        UsuarioDto responseDTO = null;
        CreateUsuarioCommand command = null;
        LDAPUsuarioDto agregar = new LDAPUsuarioDto();

        try {
            entity = UsuarioMapper.mapDtoToEntity(usuarioDto);
            command = CommandFactory.createCreateUsuarioCommand(entity);
            command.execute();
            responseDTO = UsuarioMapper.mapEntityToDto(command.getReturnParam());


        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CustomResponse<>("Error interno al momento de crear un usuario", e.getMessage())).build();
        } finally {

            if (LDAPUsuarioDto.autenticarUsuario(usuarioDto.get_alias(), usuarioDto.get_clave()) == false){
                agregar.agregarUsuario(usuarioDto.get_alias(), usuarioDto.get_clave());
            }

            if (command != null)
                command.closeHandlerSession();
        }

        return Response.status(Response.Status.OK).entity(new CustomResponse<>(responseDTO, "El usuario ha sido creado correctamente")).build();
    }


    /**
     * Elimina un usuario por su ID.
     *
     * @param usuarioId ID del usuario a eliminar.
     * @return La respuesta HTTP con el usuario eliminado o un mensaje de error si no se puede eliminar.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteUsuario(@PathParam("id") long usuarioId) {
        Usuario entity;
        UsuarioDto responseDTO = null;
        DeleteUsuarioCommand command = null;

        try {
            entity = UsuarioMapper.mapDtoToEntity(usuarioId);
            command = CommandFactory.createDeleteUsuarioCommand(entity);
            command.execute();

            if (command.getReturnParam() != null)
                responseDTO = UsuarioMapper.mapEntityToDto(command.getReturnParam());
            else
                return Response.status(Response.Status.OK).entity(new CustomResponse<>("No se pudo eliminar el usuario con ese ID")).build();


        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CustomResponse<>("Error interno al momento de eliminar un usuario", e.getMessage())).build();
        } finally {
            if (command != null)
                command.closeHandlerSession();
        }

        return Response.status(Response.Status.OK).entity(new CustomResponse<>(responseDTO, "El usuario ha sido eliminado correctamente")).build();
    }

    /**
     * Actualiza los datos de un usuario.
     *
     * @param usuarioDto El objeto `UsuarioDto` que representa los nuevos datos del usuario.
     * @return La respuesta HTTP con el usuario actualizado o un mensaje de error si no se puede actualizar.
     */
    @PUT
    public Response updateUsuario(UsuarioDto usuarioDto) {
        Usuario entity;
        UsuarioDto responseDTO = null;
        UsuarioDto ldap = null;
        UpdateUsuarioCommand commandAct = null;
        GetUsuarioCommand CommandObt = null;
        LDAPUsuarioDto update = new LDAPUsuarioDto();

        try {

            entity = UsuarioMapper.mapDtoToEntity(usuarioDto.getId());
            CommandObt = CommandFactory.createGetUsuarioCommand(entity);
            CommandObt.execute();

            if (CommandObt.getReturnParam() != null) {
                ldap = UsuarioMapper.mapEntityToDto(CommandObt.getReturnParam());
            }
            else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CustomResponse<>("[GENERAL EXCEPTION] at method updateUser, user with id " + usuarioDto.getId() + " could not be found")).build();
            }

            entity = UsuarioMapper.mapDtoToEntity(usuarioDto);
            commandAct = CommandFactory.createUpdateUsuarioCommand(entity);
            commandAct.execute();
            if (commandAct.getReturnParam() != null) {
                responseDTO = UsuarioMapper.mapEntityToDto(commandAct.getReturnParam());
            }
            else {
                return Response.status(Response.Status.OK).entity(new CustomResponse<>("No se pudo editar el ID: " + usuarioDto.getId()) + " debido a que no existe en la base de datos").build();
            }

        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new CustomResponse<>("Error interno al actualizar el usuario: " + e.getMessage())).build();

        } finally {

            if (LDAPUsuarioDto.autenticarUsuario(ldap.get_alias(), ldap.get_clave()) == true) {
                update.actualizarClaveUsuario(ldap.get_alias(), usuarioDto.get_clave());
            }

            if (commandAct != null)
                commandAct.closeHandlerSession();
            if (CommandObt != null)
                CommandObt.closeHandlerSession();
        }
        return Response.status(Response.Status.OK).entity(new CustomResponse<>(responseDTO, "El usuario con el ID " + usuarioDto.getId() + " se actualizo correctamente")).build();
    }
}
