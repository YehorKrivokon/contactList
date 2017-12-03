package contactList.app.service.business_trip;

import contactList.app.dao.BusinessTripDao;
import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by комп on 07.04.2017.
 */
@Service
public class BusinessTripServiceImpl implements BusinessTripService {
    @Autowired
    private BusinessTripDao businessTripDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(BusinessTrip businessTrip) {
        businessTripDao.add(businessTrip);
    }

    @Override
    public void update(BusinessTrip businessTrip) {
        businessTripDao.updateBusinessTrip(businessTrip);
    }

    @Override
    public List<BusinessTrip> getEmployeeBusinessTripList(Contact contact) {
        return businessTripDao.getEmployeeBusinessTripList(contact);
    }

    @Override
    public List<BusinessTrip> getAllBusinessTrips() {
        return businessTripDao.getAllBusinessTrips();
    }

    @Override
    public BusinessTrip getBusinessTripById(Long id) {
        return businessTripDao.findByBusinessTripId(id);
    }

    @Override
    public void delete(BusinessTrip businessTrip) {
        businessTripDao.delete(businessTrip);
    }
}
