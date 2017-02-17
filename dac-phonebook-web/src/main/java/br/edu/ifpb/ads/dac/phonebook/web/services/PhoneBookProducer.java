/**
*/
package br.edu.ifpb.ads.dac.phonebook.web.services;

import br.edu.ifpb.ads.dac.phonebook.shared.interfaces.PhoneBook;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 * A class to produce the PhoneBooks instance
 * 
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 16/02/2017 - 12:08:30
 */
@ApplicationScoped
public class PhoneBookProducer {
    
    private static final String RESOURCE = "java:global/dac-phonebook-core/PhoneBookImpl";
    
    /**
     * The method which return a PhoneBook instance from a remote project.
     * 
     * @return a instance of PhoneBook class.
     */
    @Default
    @Produces
    @Deprecated
    public PhoneBook produce(){
        return new ServiceLocator().lookup(RESOURCE, PhoneBook.class);
    }
}
