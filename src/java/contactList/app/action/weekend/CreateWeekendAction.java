package contactList.app.action.weekend;

import contactList.app.model.Dayoff;
import contactList.app.model.Weekend;
import contactList.app.service.dayoff.DayoffService;
import contactList.app.service.weekend.WeekendService;

/**
 * Created by комп on 07.04.2017.
 */
public class CreateWeekendAction extends WeekendAction {
    public CreateWeekendAction(WeekendService weekendService, Weekend weekend) {
        super(weekendService, weekend);
    }

    @Override
    public void execute() {
        weekendService.save(weekend);
    }
}
