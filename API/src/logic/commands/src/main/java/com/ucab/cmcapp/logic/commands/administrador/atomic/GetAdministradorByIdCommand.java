package com.ucab.cmcapp.logic.commands.administrador.atomic;

import com.ucab.cmcapp.common.entities.Administrador;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.AdministradorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetAdministradorByIdCommand extends Command<Administrador> {
    private static Logger _logger = LoggerFactory.getLogger(GetAdministradorByIdCommand.class);
    private long _adminId;
    private Administrador _result;
    private AdministradorDao _dao;

    public GetAdministradorByIdCommand(DBHandler handler, long adminId) {
        try {
            //region Instrumentation DEBUG
            _logger.debug(String.format("Tomar de GetAdministradorByIdCommand.ctor: parameter {%s}", adminId));
            //endregion

            _adminId = adminId;
            setHandler(handler);
            _dao = DaoFactory.createAdministradorDao(getHandler());

            //region Instrumentation DEBUG
            _logger.debug(String.format("Dejando GetAdministradorByIdCommand.ctor: attribute {%s}", adminId));
            //endregion
        }catch(Exception e){

        }
    }

    @Override
    public void execute() {
        //region Instrumentation DEBUG
        _logger.debug("Tomando de  GetAdministradorByIdCommand.execute");
        //endregion
        try {
            _result = _dao.find(_adminId, Administrador.class);
        }catch(NullPointerException e){

        }
        //region Instrumentation DEBUG
        _logger.debug("Dejando GetAdministradorByIdCommand.execute");
        //endregion
    }

    @Override
    public Administrador getReturnParam() {
        return _result;
    }

    @Override
    public void closeHandlerSession() {
        getHandler().closeSession();
    }

    public void setDao(AdministradorDao administradorDao) {

    }
}
