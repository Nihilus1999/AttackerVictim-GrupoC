package com.ucab.cmcapp.logic.commands.relacion_VA.atomic;

import com.ucab.cmcapp.common.entities.Relacion_VA;
import com.ucab.cmcapp.logic.commands.Command;
import com.ucab.cmcapp.persistence.DBHandler;
import com.ucab.cmcapp.persistence.DaoFactory;
import com.ucab.cmcapp.persistence.dao.Relacion_VADao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddRelacionCommand extends Command<Relacion_VA> {
    private static Logger _logger = LoggerFactory.getLogger(AddRelacionCommand.class);
    private Relacion_VA _Relacion_VA;
    private Relacion_VADao _dao;

    public AddRelacionCommand(Relacion_VA Relacion_VA, DBHandler handler) {

        _logger.debug(String.format("Get in AddRelacion_VACommand.ctor: parameter {%s}",
                Relacion_VA.toString()));

        setHandler(handler);
        _Relacion_VA = Relacion_VA;
        _dao = DaoFactory.createRelacion_VADao(getHandler());


        _logger.debug(String.format("Leaving AddRelacion_VACommand.ctor: attribute {%s}",
                _Relacion_VA.toString()));
    }

    @Override
    public void execute() {
        _logger.debug("Tomando en AddRelacion_VACommand.execute");
        try {
            _Relacion_VA = _dao.insert(_Relacion_VA);
        }catch(NullPointerException e){

        }

        _logger.debug("Dejando AddRelacion_VACommand.execute");
    }

    @Override
    public Relacion_VA getReturnParam() {
        return _Relacion_VA;
    }

    @Override
    public void closeHandlerSession() {
        getHandler().closeSession();
    }

    public void setDao(Relacion_VADao relacionVADao) {

    }
}
