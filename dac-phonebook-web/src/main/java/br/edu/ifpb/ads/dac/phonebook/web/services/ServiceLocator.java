package br.edu.ifpb.ads.dac.phonebook.web.services;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * A ServiceLocator to recover a instance from a Remote JNDI instance.
 * 
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 16/02/2017 - 12:47:54
 */
public class ServiceLocator {
    
    /**
     * Method to execulte a lookup of class witch the pass paramter in a remote
     * JNDI project.
     * 
     * @param <T> The Type of return instance.
     * @param recurso The JNDI remote address.
     * @param tipo Type of interface used in the Remote bean anotattion.
     * @return A instance of requisited class.
     */
    public <T> T lookup(String recurso, Class<T> tipo) {
        try {
            Properties props = new Properties();

//            props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
            props.setProperty("org.omg.CORBA.ORBInitialHost", "dac-phonebook-core");
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            
            props.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty(Context.URL_PKG_PREFIXES,
                    "com.sun.enterprise.naming");
            props.setProperty(Context.STATE_FACTORIES,
                    "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            
            props.put(Context.SECURITY_AUTHENTICATION, "simple");
            props.put(Context.SECURITY_PRINCIPAL, "admin");
            props.put(Context.SECURITY_CREDENTIALS, "admin");

            InitialContext context = new InitialContext(props);

            return (T) context.lookup(recurso);
            
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
