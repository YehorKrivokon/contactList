package contactList.app.dao;

import contactList.app.model.Contact;
import contactList.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by комп on 05.04.2017.
 */
public interface ContactDao {
    Contact findByContactId(Long id);
    void updateContact(Contact contactToUpgrade);
    void add(Contact contact);
    void delete(Contact duty);

    List<Contact> getUserContactList(User user);
}
