/**
 */
package br.edu.ifpb.ads.dac.phonebook.core.persistence;

import br.edu.ifpb.ads.dac.phonebook.core.persistence.interfaces.PhoneBooks;
import br.edu.ifpb.ads.dac.phonebook.shared.entities.Contact;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 * A PhoneBooks implementation for persistence JDBC 
 * 
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 16/02/2017 - 00:13:38
 */
@Default
@Stateless
@Local(PhoneBooks.class)
public class PhoneBooksJDBC implements PhoneBooks {

    @Override
    public void persist(Contact contact) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionProvider.getConnection();
            String string = "INSERT INTO CONTACT(NAME, EMAIL, NUMBER) VALUES(?, ?, ?)";
            ps = connection.prepareStatement(string);

            int i = 1;
            ps.setString(i++, contact.getName());
            ps.setString(i++, contact.getEmail());
            ps.setString(i++, contact.getNumber());

            ps.executeUpdate();

        } catch (IOException | SQLException | URISyntaxException ex) {
            Logger.getLogger(PhoneBooksJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        closeResources(connection, ps);
    }

    @Override
    public void delete(Long id) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionProvider.getConnection();
            String string = "DELETE FROM CONTACT WHERE ID = ?";
            ps = connection.prepareStatement(string);
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (IOException | SQLException | URISyntaxException ex) {
            Logger.getLogger(PhoneBooksJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeResources(connection, ps);
    }

    @Override
    public void update(Contact contact) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionProvider.getConnection();
            String string = "UPDATE CONTACT SET NAME = ?, EMAIL = ?, NUMBER = ? WHERE ID = ?";
            ps = connection.prepareStatement(string);

            int i = 1;
            ps.setString(i++, contact.getName());
            ps.setString(i++, contact.getEmail());
            ps.setString(i++, contact.getNumber());
            ps.setLong(i++, contact.getId());

            ps.executeUpdate();

        } catch (IOException | SQLException | URISyntaxException ex) {
            Logger.getLogger(PhoneBooksJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeResources(connection, ps);
    }

    @Override
    public List<Contact> listOrderByName() {
        Connection connection = null;
        PreparedStatement ps = null;
        List<Contact> contacts = new ArrayList<>();

        try {
            connection = ConnectionProvider.getConnection();
            String string = "SELECT * FROM CONTACT ORDER BY(NAME)";
            ps = connection.prepareStatement(string);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                contacts.add(fillContact(rs));
            }

        } catch (IOException | SQLException  | URISyntaxException ex) {
            Logger.getLogger(PhoneBooksJDBC.class.getName()).log(Level.SEVERE, null, ex);
            closeResources(connection, ps);
            return Collections.EMPTY_LIST;
        }
        
        closeResources(connection, ps);
        return contacts;
    }

    @Override
    public List<Contact> searchContactByName(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        List<Contact> contacts = new ArrayList<>();

        try {
            connection = ConnectionProvider.getConnection();
            String string = "SELECT * FROM CONTACT WHERE NAME ilike ? ORDER BY(NAME)";
            ps = connection.prepareStatement(string);

            ps.setString(1, name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                contacts.add(fillContact(rs));
            }

        } catch (IOException | SQLException | URISyntaxException ex) {
            Logger.getLogger(PhoneBooksJDBC.class.getName()).log(Level.SEVERE, null, ex);
            closeResources(connection, ps);
            return Collections.EMPTY_LIST;
        }
        
        closeResources(connection, ps);
        return contacts;
    }
    
    private Contact fillContact(ResultSet rs) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getLong("ID"));
        contact.setName(rs.getString("NAME"));
        contact.setEmail(rs.getString("EMAIL"));
        contact.setNumber(rs.getString("NUMBER"));
        return contact;
    }

    private void closeResources(Connection connection, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PhoneBooksJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
