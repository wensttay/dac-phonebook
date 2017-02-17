/**
 */
package br.edu.ifpb.ads.dac.phonebook.shared.entities;

import java.io.Serializable;

/**
 * The contact representation which contains all of your attributes.
 * 
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 15/02/2017 - 23:53:16
 */
public class Contact implements Serializable{

    private Long id;
    private String name;
    private String email;
    private String number;
    
    public Contact() {
    }

    public Contact(Long id, String name, String email, String number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
    }

    public Contact(String name, String email, String number) {
        this.name = name;
        this.email = email;
        this.number = number;
    }
    
    @Override
    public String toString() {
        return "Contact{" + "id=" + getId() + ", name=" + getName() + ", email=" + getEmail() + ", number=" + getNumber() + '}';
    }    

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
    
}
