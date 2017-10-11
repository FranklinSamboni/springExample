/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boletic.web.facade;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import boletic.utils.Constantes;
import boletic.utils.HibernateUtil;
import boletic.web.vo.ClienteVO;

/**
 *
 * @author Administrador
 */
@Repository
public class ClienteFacade {

    private static final Log logger = LogFactory.getLog(ClienteFacade.class);
    
    public ClienteFacade() {

    }

    public void crearCliente(ClienteVO cliente) {

        Transaction transaction = null;
        Session session = HibernateUtil.currentSession();
        try {
            transaction = session.beginTransaction();
            
            String sql = "INSERT INTO cliente (cedula, nombre, telefono, correo) VALUES (:cedula, :nombre, :telefono, :correo)";
            
            int res = session.createSQLQuery(sql).
                setString("cedula", cliente.getCedula()).
                setString("nombre", cliente.getNombre()).
                setString("telefono", cliente.getTelefono()).
                setString("correo", cliente.getCorreo()).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            logger.error("CrearCliente", e);
            if (transaction != null) { 
                transaction.rollback(); 
            }
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

}
