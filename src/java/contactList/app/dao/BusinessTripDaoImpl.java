package contactList.app.dao;

import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.service.business_trip.BusinessTripService;
import contactList.app.service.security.SecurityService;
import contactList.app.service.user.UserService;
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
public class BusinessTripDaoImpl implements BusinessTripDao{

    private Query query;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BusinessTripService businessTripService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BusinessTrip findByBusinessTripId(Long id) {
        query = entityManager.createQuery("SELECT c FROM BusinessTrip c where c.id = :id", BusinessTrip.class);
        query.setParameter("id", id);
        return (BusinessTrip) query.getSingleResult();
    }

    @Transactional
    @Override
    public void updateBusinessTrip(BusinessTrip businessTripToUpgrade) {
        entityManager.merge(businessTripToUpgrade);
    }

    @Transactional
    @Override
    public void add(BusinessTrip businessTrip) {
        entityManager.merge(businessTrip);
    }

    @Transactional
    @Override
    public void delete(BusinessTrip businessTrip) {
        entityManager.remove(entityManager.contains(businessTrip) ?
                businessTrip : entityManager.merge(businessTrip));
    }

    @Override
    public List<BusinessTrip> getEmployeeBusinessTripList(Contact contact) {
        query = entityManager.createQuery("SELECT DISTINCT c FROM BusinessTrip c INNER JOIN c.contact g WHERE g.id = :contact1", BusinessTrip.class);
        query.setParameter("contact1", contact.getId());
        return (List<BusinessTrip>) query.getResultList();
    }

    @Override
    public List<BusinessTrip> getAllBusinessTrips() {
        query = entityManager.createQuery("SELECT DISTINCT b FROM BusinessTrip b", BusinessTrip.class);
        return (List<BusinessTrip>) query.getResultList();
    }
}