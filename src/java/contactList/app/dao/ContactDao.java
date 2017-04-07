package contactList.app.dao;

import contactList.app.model.Contact;
import contactList.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by комп on 05.04.2017.
 */
public interface ContactDao extends JpaRepository<Contact,Long> {
}
