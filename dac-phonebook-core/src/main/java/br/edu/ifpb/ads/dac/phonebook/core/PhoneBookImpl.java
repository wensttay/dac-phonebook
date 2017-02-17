/**
*/
package br.edu.ifpb.ads.dac.phonebook.core;

import br.edu.ifpb.ads.dac.phonebook.core.persistence.interfaces.PhoneBooks;
import br.edu.ifpb.ads.dac.phonebook.shared.entities.Contact;
import br.edu.ifpb.ads.dac.phonebook.shared.interfaces.PhoneBook;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Class to promove a remove instances for Remote projects.
 * 
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 16/02/2017 - 00:11:39
 */
@Stateless
@Remote(PhoneBook.class)
public class PhoneBookImpl implements PhoneBook{

    @EJB
    PhoneBooks phoneBooks;
    
    @Override
    public void addContact(Contact contact) {
        phoneBooks.persist(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        phoneBooks.update(contact);
    }

    @Override
    public void removeContact(Long id) {
        phoneBooks.delete(id);
    }

    @Override
    public List<Contact> listOrderByName() {
        return phoneBooks.listOrderByName();
    }

    @Override
    public List<Contact> searchContactByName(String contactName) {
        return phoneBooks.searchContactByName(contactName);
    }
    
}
