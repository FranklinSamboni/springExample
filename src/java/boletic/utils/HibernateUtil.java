/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boletic.utils;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Administrador
 */
public class HibernateUtil {
    
    private static final Log logger = LogFactory.getLog(HibernateUtil.class);
    private static SessionFactory sessionFactory;
    public static final ThreadLocal session = new ThreadLocal();
    
    private static SessionFactory getSessionFactory(){
        try{
            if(sessionFactory == null){
                Configuration configuration = new Configuration().configure();
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
                Properties parameters = configuration.getProperties();
                sessionFactory = configuration.buildSessionFactory(builder.applySettings(parameters).build());
            }
        }catch(HibernateException e){
            logger.error("GetSessionFactory Error", e);
            throw new ExceptionInInitializerError(e);
        }
        return sessionFactory;
    }
    
    public static Session currentSession() throws HibernateException {
        Session s = (Session) session.get();
        if(s == null){
            s = getSessionFactory().openSession();
            session.set(s);
        }
        return s;
    }
    
    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if(s != null){
            s.close();
        }
    }
    
}
