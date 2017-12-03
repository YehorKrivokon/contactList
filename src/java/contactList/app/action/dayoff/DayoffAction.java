package contactList.app.action.dayoff;

import contactList.app.action.Action;
import contactList.app.model.BusinessTrip;
import contactList.app.model.Dayoff;
import contactList.app.service.business_trip.BusinessTripService;
import contactList.app.service.dayoff.DayoffService;

/**
 * Created by комп on 07.04.2017.
 */
public abstract class DayoffAction implements Action {
    protected DayoffService dayoffService;
    protected Dayoff dayoff;

    public DayoffAction(DayoffService dayoffService, Dayoff dayoff){
        this.dayoff = dayoff;
        this.dayoffService = dayoffService;
    }
}
