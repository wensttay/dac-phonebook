/**
*/
package br.edu.ifpb.ads.dac.phonebook.web;

import br.edu.ifpb.ads.dac.phonebook.shared.entities.Contact;
import java.util.List;

/**
 * The class to represent a Group of Contacts.
 * 
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 16/02/2017 - 12:47:54
 */
public class ContactsGroup {
    
    private String groupName;
    private List<Contact> contacts;
    
    public ContactsGroup(String groupName, List<Contact> contacts) {
        this.groupName = groupName;
        this.contacts = contacts;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * @return the contacts
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

     
}
