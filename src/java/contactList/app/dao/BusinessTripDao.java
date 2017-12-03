package contactList.app.dao;

import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.User;

import java.util.List;

/**
 * Created by комп on 05.04.2017.
 */
public interface BusinessTripDao {
    BusinessTrip findByBusinessTripId(Long id);
    void updateBusinessTrip(BusinessTrip businessTripToUpgrade);
    void add(BusinessTrip businessTrip);
    void delete(BusinessTrip businessTrip);
    List<BusinessTrip> getAllBusinessTrips();
    List<BusinessTrip> getEmployeeBusinessTripList(Contact contact);
}
