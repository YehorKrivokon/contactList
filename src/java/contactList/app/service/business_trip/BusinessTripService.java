package contactList.app.service.business_trip;

import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.User;

import java.util.List;

/**
 * Created by комп on 07.04.2017.
 */
public interface BusinessTripService {
    void save(BusinessTrip businessTrip);
    void update(BusinessTrip businessTrip);
    List<BusinessTrip> getEmployeeBusinessTripList(Contact contact);
    List<BusinessTrip> getAllBusinessTrips();
    BusinessTrip getBusinessTripById(Long id);
    void delete(BusinessTrip businessTrip);
}

