package contactList.app.action.weekend;

import contactList.app.action.Action;
import contactList.app.model.Dayoff;
import contactList.app.model.Weekend;
import contactList.app.service.dayoff.DayoffService;
import contactList.app.service.weekend.WeekendService;

/**
 * Created by комп on 07.04.2017.
 */
public abstract class WeekendAction implements Action {
    protected WeekendService weekendService;
    protected Weekend weekend;

    public WeekendAction(WeekendService weekendService, Weekend weekend){
        this.weekend = weekend;
        this.weekendService = weekendService;
    }
}
