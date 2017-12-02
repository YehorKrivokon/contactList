package contactList.app.action.business_trip;

import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.service.business_trip.BusinessTripService;
import contactList.app.service.contact.ContactService;

/**
 * Created by комп on 07.04.2017.
 */
public class UpdateBusinessTripAction extends BusinessTripAction {
    public UpdateBusinessTripAction(BusinessTripService businessTripService, BusinessTrip businessTrip) {
        super(businessTripService, businessTrip);
    }

    @Override
    public void execute() {
        businessTripService.update(businessTrip);
    }
}
