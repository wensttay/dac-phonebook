/**
 */
package br.edu.ifpb.ads.dac.phonebook.web;

import br.edu.ifpb.ads.dac.phonebook.shared.entities.Contact;
import br.edu.ifpb.ads.dac.phonebook.shared.interfaces.PhoneBook;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Class for controll a JSF page with a Contact CRUD.
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 16/02/2017 - 04:59:55
 */
@Named
@SessionScoped
public class ContactsControll implements Serializable {

    @Inject
    private PhoneBook booksJDBC;
    private List<ContactsGroup> contactGroupsList = new ArrayList<>();
    private Contact contact;
    private boolean editing;

    @PostConstruct
    private void postConstruct() {
        getActualizedList();
        this.setContact(new Contact());
        this.setEditing(false);
    }

    /**
     * This method search for all Contacts order by name and organize in
     * distincts ContactsGrops by the first letter.
     *
     * @return a List with all ContactsGrops ordey by name.
     */
    private List<ContactsGroup> getActualizedList() {
        List<Contact> listOrderByName = booksJDBC.listOrderByName();
        List<ContactsGroup> cgs = new ArrayList<>();

        if (!listOrderByName.isEmpty()) {
            List<Contact> contacts = new ArrayList<>();

            String name = listOrderByName.get(0).getName().toUpperCase().trim();
            char firstLetter = name.charAt(0);
            contacts.add(listOrderByName.get(0));

            if (listOrderByName.size() == 1) {
                cgs.add(new ContactsGroup("" + firstLetter, contacts));
            } else {
                for (int i = 1; i < listOrderByName.size(); i++) {
                    String otherName = listOrderByName.get(i).getName().toUpperCase().trim();

                    if (otherName.charAt(0) == firstLetter) {
                        contacts.add(listOrderByName.get(i));
                    } else {
                        cgs.add(new ContactsGroup("" + firstLetter, contacts));
                        firstLetter = otherName.charAt(0);
                        contacts = new ArrayList<>();
                        contacts.add(listOrderByName.get(i));
                    }
                }
                
                if (!contacts.isEmpty()) {
                    cgs.add(new ContactsGroup("" + firstLetter, contacts));
                }
            }

        }

        return cgs;
    }

    /**
     * This method clean the form inputs form the JSF page.
     *
     */
    public void clearInputs() {
        this.getContact().setName("");
        this.getContact().setEmail("");
        this.getContact().setNumber("");
    }

    /**
     * This method change the status from editing == true to false, and reset a
     * Contact variable.
     *
     */
    public void editExit() {
        this.setEditing(false);
        this.setContact(new Contact());
    }

    /**
     * Update the ContactsGroupList.
     *
     * @return the directory page to forward.
     */
    public String actualizeList() {
        this.setContact(new Contact());
        this.setEditing(false);
        this.contactGroupsList = getActualizedList();
        return null;
    }

    /**
     * Persist the new Contact.
     *
     * @return the directory page to forward.
     */
    public String addContact() {
        if (!validContact(getContact())) {
            return null;
        }
        this.booksJDBC.addContact(this.getContact());
        this.setContact(new Contact());
        return actualizeList();
    }

    /**
     * Check if the Contact is valid
     *
     * @return
     */
    private boolean validContact(Contact contact) {
        return !(this.getContact().getEmail() == null
                || this.getContact().getEmail().equals("")
                || this.getContact().getName() == null
                || this.getContact().getName().equals("")
                || this.getContact().getNumber() == null
                || this.getContact().getNumber().equals(""));
    }

    /**
     * Delete a Contact
     *
     * @param contactID
     * @return the directory page to forward.
     */
    public String removeContact(Long contactID) {
        this.booksJDBC.removeContact(contactID);

        if (isEditing() && getContact().getId().equals(contactID)) {
            this.setContact(new Contact());
            this.setEditing(false);
        }

        return actualizeList();
    }

    /**
     * Change the editing status to true
     *
     * @param contact Contact to be Updatabed
     */
    public void editContact(Contact contact) {
        this.setEditing(true);
        this.setContact(contact);
    }

    /**
     * Update the Contact atributes.
     *
     * @return the directory page to forward.
     */
    public String updateContact() {
        if (!validContact(getContact())) {
            return null;
        }
        this.booksJDBC.updateContact(this.getContact());
        this.setContact(new Contact());
        this.setEditing(false);
        return actualizeList();
    }

    /**
     * @return the contactGroupsList
     */
    public List<ContactsGroup> getContactGroupsList() {
        return getActualizedList();
    }

    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return the editing
     */
    public boolean isEditing() {
        return editing;
    }

    /**
     * @param editing the editing to set
     */
    public void setEditing(boolean editing) {
        this.editing = editing;
    }

}
