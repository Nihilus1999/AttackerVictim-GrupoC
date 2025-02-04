package com.ucab.cmcapp.persistence.dao;

import com.ucab.cmcapp.common.EntityFactory;
import com.ucab.cmcapp.common.entities.Historico_Usuario;
import com.ucab.cmcapp.common.entities.Usuario;
import com.ucab.cmcapp.common.exceptions.CupraException;
import com.ucab.cmcapp.common.exceptions.NotFoundException;
import com.ucab.cmcapp.persistence.DBHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class Historico_UsuarioDao extends BaseDao<Historico_Usuario> {
    private static Logger _logger = LoggerFactory.getLogger(Historico_UsuarioDao.class);
    private EntityManager _em;
    private CriteriaBuilder _builder;


    public Historico_UsuarioDao() {
        super();
    }

    public Historico_UsuarioDao(DBHandler handler) {
        super(handler);

        _em = getDBHandler().getSession();
        _builder = _em.getCriteriaBuilder();
    }

    /**
     * devuelve una lista de historico por el id del usuario ingresado
     * @param usuarioId
     * @return retorna una lista de historico del usuario
     */

    public List<Historico_Usuario> getAllHistoricoByUserId(Usuario usuarioId) {
        List<Historico_Usuario> results;
        _logger.debug(String.format("tomando de Historico_UsuarioDao.getHistoricoByUsuario: parametro {%s}", usuarioId));
        try {
            CriteriaQuery<Historico_Usuario> query = _builder.createQuery(Historico_Usuario.class);
            Root<Historico_Usuario> root = query.from(Historico_Usuario.class);

            query.select(root);
            query.where(_builder.equal(root.get("_usuario"), usuarioId));

            results = _em.createQuery(query).getResultList();

            if (results.isEmpty()) // Retornar null en lugar de []
                return null;

        } catch (NoResultException e) {
            _logger.error( String.format( "Error Historico_UsuarioDao.getHistoricoByUsuario: No Result {%s}", e.getMessage() ) );
            throw new NotFoundException("Historico del Usuario no existe");
        } catch (Exception e) {
            _logger.error( String.format( "Error Historico_UsuarioDao.getHistoricoByUsuario: No Result {%s}", e.getMessage() ) );
            throw new CupraException(e.getMessage());
        }
        _logger.debug(String.format("Dejando Historico_UsuarioDao.getHistoricoByUsuario: result {%s}", results));
        return results;
    }
}
