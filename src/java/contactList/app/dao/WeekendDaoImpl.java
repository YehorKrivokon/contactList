package contactList.app.dao;

import contactList.app.model.Contact;
import contactList.app.model.Dayoff;
import contactList.app.model.Weekend;
import contactList.app.service.dayoff.DayoffService;
import contactList.app.service.security.SecurityService;
import contactList.app.service.weekend.WeekendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by комп on 05.04.2017.
 */
@Repository
public class WeekendDaoImpl implements WeekendDao {

    private Query query;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private WeekendService weekendService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Weekend findByWeekendId(Long id) {
        query = entityManager.createQuery("SELECT c FROM Weekend c where c.id = :id", Weekend.class);
        query.setParameter("id", id);
        return (Weekend) query.getSingleResult();
    }

    @Transactional
    @Override
    public void updatWeekend(Weekend weekendToUpgrade) {
        entityManager.merge(weekendToUpgrade);
    }

    @Transactional
    @Override
    public void add(Weekend weekend) {
        entityManager.merge(weekend);
    }

    @Override
    public List<Weekend> getAllWeekends() {
        query = entityManager.createQuery("SELECT DISTINCT b FROM Weekend b", Weekend.class);
        return (List<Weekend>) query.getResultList();
    }

    @Override
    public List<Weekend> getEmployeeWeekendList(Contact contact) {
        query = entityManager.createQuery("SELECT DISTINCT c FROM Weekend c INNER JOIN c.contact g WHERE g.id = :contact1", Weekend.class);
        query.setParameter("contact1", contact.getId());
        return (List<Weekend>) query.getResultList();
    }
}