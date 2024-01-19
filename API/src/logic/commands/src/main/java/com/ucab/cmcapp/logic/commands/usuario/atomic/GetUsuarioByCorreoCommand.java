package com.ucab.cmcapp.logic.commands.usuario.atomic;

import com.ucab.cmcapp.common.entities.Usuario;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.UsuarioDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUsuarioByCorreoCommand extends Command<Usuario> {
    private static Logger _logger = LoggerFactory.getLogger(GetUsuarioByCorreoCommand.class);
    private Usuario _usuario;
    private UsuarioDao _dao;

    public GetUsuarioByCorreoCommand(Usuario usuario) {

        _logger.debug(String.format("Tomando de GetUsuarioByCorreoCommand.ctor: parameter {%s}", usuario.toString()));

        _usuario = usuario;
        setHandler(new DBHandler());
        _dao = DaoFactory.createUsuarioDao(getHandler());

        _logger.debug(String.format("Dejando GetUsuarioByCorreoCommand.ctor: atribute {%s}", _usuario.toString()));

    }

    @Override
    public void execute() {

        _logger.debug("Tomando GetUsuarioByCorreoCommand.execute");
        try{
            _usuario = _dao.getUsuarioByCorreo(_usuario.get_correo());
        }catch(NullPointerException e){

        }
        _logger.debug("Dejando GetUsuarioByCorreoCommand.execute");
    }

    @Override
    public Usuario getReturnParam() {
        return _usuario;
    }

    @Override
    public void closeHandlerSession() {
        getHandler().closeSession();
    }

    public void setDao(UsuarioDao usuarioDao) {

    }
}
