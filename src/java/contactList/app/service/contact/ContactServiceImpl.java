package contactList.app.service.contact;

import contactList.app.dao.ContactDao;
import contactList.app.model.Contact;
import contactList.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by комп on 07.04.2017.
 */
@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    private ContactDao contactDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Contact contact) {
        contactDao.add(contact);
    }

    @Override
    public void update(Contact contact) {
        entityManager.merge(contact);
    }

    @Override
    public List<Contact> getUserContactList(User user) {
        return contactDao.getUserContactList(user);
    }
}