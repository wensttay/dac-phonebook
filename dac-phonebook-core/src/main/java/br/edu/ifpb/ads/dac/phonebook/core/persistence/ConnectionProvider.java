/**
 */
package br.edu.ifpb.ads.dac.phonebook.core.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.postgresql.ds.PGPoolingDataSource;

/**
 * Class to provide a JDBC connection.
 * 
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 16/02/2017 - 00:40:01
 */
public class ConnectionProvider implements Serializable{

    private static final String BD_PROPERTIES = "/banco/bd.properties";
    private static PGPoolingDataSource pGPoolingDataSource = null;
    
    /**
     * Method to config a JDBC connection.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws URISyntaxException 
     */
    private static void init() throws FileNotFoundException, IOException, URISyntaxException {
        Properties properties = new Properties();
        String path = ConnectionProvider.class.getClassLoader().getResource(BD_PROPERTIES).toURI().getPath();
        properties.load(new FileInputStream(path));
        
        pGPoolingDataSource = new PGPoolingDataSource();
        
        pGPoolingDataSource.setServerName(properties.getProperty("HOST"));
        pGPoolingDataSource.setPortNumber(Integer.valueOf(properties.getProperty("PORT")));
        pGPoolingDataSource.setDatabaseName(properties.getProperty("DATABASE"));

        pGPoolingDataSource.setUser(properties.getProperty("USER"));
        pGPoolingDataSource.setPassword(properties.getProperty("PASSWORD"));
        pGPoolingDataSource.setMaxConnections(5);
    }
    
    /**
     * Method to get a JDBC Connection.
     * 
     * @return a JDBC Connection.
     * @throws IOException
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws URISyntaxException 
     */
    public static Connection getConnection() throws IOException, SQLException, FileNotFoundException, URISyntaxException {
        if (pGPoolingDataSource == null) {
            init();
        }
        return pGPoolingDataSource.getConnection();
    }

}
