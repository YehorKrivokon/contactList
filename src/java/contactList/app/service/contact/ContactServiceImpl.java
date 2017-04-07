package contactList.app.service.contact;

import contactList.app.dao.ContactDao;
import contactList.app.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        contactDao.save(contact);
    }

    @Transactional
    @Override
    public void update(Contact contact) {
        entityManager.merge(contact);
    }
}
