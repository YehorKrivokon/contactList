package contactList.app.dao;

import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.Dayoff;
import contactList.app.service.business_trip.BusinessTripService;
import contactList.app.service.dayoff.DayoffService;
import contactList.app.service.security.SecurityService;
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
public class DayoffDaoImpl implements DayoffDao{

    private Query query;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private DayoffService dayoffService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Dayoff findByDayoffId(Long id) {
        query = entityManager.createQuery("SELECT c FROM Dayoff c where c.id = :id", Dayoff.class);
        query.setParameter("id", id);
        return (Dayoff) query.getSingleResult();
    }

    @Transactional
    @Override
    public void updatDayoff(Dayoff dayoffToUpgrade) {
        entityManager.merge(dayoffToUpgrade);
    }

    @Transactional
    @Override
    public void add(Dayoff dayoff) {
        entityManager.merge(dayoff);
    }

    @Transactional
    @Override
    public void delete(Dayoff dayoff) {
        entityManager.remove(entityManager.contains(dayoff) ?
                dayoff : entityManager.merge(dayoff));
    }

    @Override
    public List<Dayoff> getAllDayoffs() {
        query = entityManager.createQuery("SELECT DISTINCT b FROM Dayoff b", Dayoff.class);
        return (List<Dayoff>) query.getResultList();
    }

    @Override
    public List<Dayoff> getEmployeeDayoffList(Contact contact) {
        query = entityManager.createQuery("SELECT DISTINCT c FROM Dayoff c INNER JOIN c.contact g WHERE g.id = :contact1", Dayoff.class);
        query.setParameter("contact1", contact.getId());
        return (List<Dayoff>) query.getResultList();
    }
}