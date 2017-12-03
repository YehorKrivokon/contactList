package contactList.app.action.dayoff;

import contactList.app.model.BusinessTrip;
import contactList.app.model.Dayoff;
import contactList.app.service.business_trip.BusinessTripService;
import contactList.app.service.dayoff.DayoffService;

/**
 * Created by комп on 07.04.2017.
 */
public class CreateDayoffAction extends DayoffAction {
    public CreateDayoffAction(DayoffService dayoffService, Dayoff dayoff) {
        super(dayoffService, dayoff);
    }

    @Override
    public void execute() {
        dayoffService.save(dayoff);
    }
}
