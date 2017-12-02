package contactList.app.action.business_trip;

import contactList.app.action.Action;
import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.service.business_trip.BusinessTripService;
import contactList.app.service.contact.ContactService;

/**
 * Created by комп on 07.04.2017.
 */
public abstract class BusinessTripAction implements Action {
    protected BusinessTripService businessTripService;
    protected BusinessTrip businessTrip;

    public BusinessTripAction(BusinessTripService businessTripService, BusinessTrip businessTrip){
        this.businessTrip = businessTrip;
        this.businessTripService= businessTripService;
    }
}
