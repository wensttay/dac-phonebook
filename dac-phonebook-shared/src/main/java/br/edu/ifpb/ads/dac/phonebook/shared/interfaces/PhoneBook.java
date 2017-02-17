/**
 */
package br.edu.ifpb.ads.dac.phonebook.shared.interfaces;

import br.edu.ifpb.ads.dac.phonebook.shared.entities.Contact;
import java.util.List;

/**
 * Interface with the methods signatures of PhoneBook implementations.
 * 
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/02/2017 - 23:52:03
 */
public interface PhoneBook {
    
    /**
     * Persist a Contact.
     * 
     * @param contact to be persisted.
     */
    public void addContact(Contact contact);
    
    /**
     * Update a Contact.
     * 
     * @param contact to be updated.
     */
    public void updateContact(Contact contact);
    
    /**
     * Remove a Contact.
     * 
     * @param id of the contact to be removed.
     */
    public void removeContact(Long id);
    
    /**
     * List all Contacts order by name.
     * 
     * @return A list with all contacts order by name.
     */
    public List<Contact> listOrderByName();
    
    /**
     * Search the Contacts by the name.
     * 
     * @param contactName
     * @return A list with all contacts with the same name as pass by paramter.
     */
    public List<Contact> searchContactByName(String contactName);
    
}
