package contactList.app.dao;

import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.User;
import contactList.app.service.security.SecurityService;
import contactList.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by комп on 05.04.2017.
 */
@Repository
public class ContactDaoImpl implements ContactDao{

    private Query query;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Contact findByContactId(Long id) {
        query = entityManager.createQuery("SELECT c FROM Contact c where c.id = :id", Contact.class);
        query.setParameter("id", id);
        return (Contact) query.getSingleResult();
    }

    @Transactional
    @Override
    public void updateContact(Contact contactToUpgrade) {
        entityManager.merge(contactToUpgrade);
    }

    @Transactional
    @Override
    public void add(Contact contact) {
        entityManager.merge(contact);
    }

    @Transactional
    @Override
    public void delete(Contact contact) {
        entityManager.remove(entityManager.contains(contact) ? contact : entityManager.merge(contact));
    }

    @Override
    public List<Contact> getContactsByDepartment(String status) {
        query = entityManager.createQuery("SELECT DISTINCT c FROM Contact c WHERE c.contactStatus = :status", Contact.class);
        query.setParameter("status", status);
        return (List<Contact>) query.getResultList();
    }

    @Override
    public List<Contact> getContactsWithDayoff(String contactStatusPage) {
        query = entityManager.createQuery("SELECT DISTINCT c FROM Contact c WHERE c.contactStatus = :status AND c.dayoffList IS NOT EMPTY", Contact.class);
        query.setParameter("status", contactStatusPage);
        return (List<Contact>) query.getResultList();
    }

    @Override
    public List<BusinessTrip> getContactBusinessTripList(Long id) {
        query = entityManager.createQuery("SELECT DISTINCT c FROM BusinessTrip c WHERE c.contact_id = :contact1", BusinessTrip.class);
        query.setParameter("contact1", id);
        return (List<BusinessTrip>) query.getResultList();
    }

    @Override
    public List<Contact> getUserContactList(User user) {
        query = entityManager.createQuery("SELECT DISTINCT c FROM Contact c INNER JOIN c.user g WHERE g.id = :user1", Contact.class);
        query.setParameter("user1", user.getId());
        return (List<Contact>) query.getResultList();
    }

}