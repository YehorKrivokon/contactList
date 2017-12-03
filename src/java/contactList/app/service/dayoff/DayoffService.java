package contactList.app.service.dayoff;

import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.Dayoff;

import java.util.List;

/**
 * Created by комп on 07.04.2017.
 */
public interface DayoffService {
    void save(Dayoff dayoff);
    List<Dayoff> getEmployeeDayoffList(Contact contact);
    List<Dayoff> getAllDayoff();
    Dayoff getDayoffById(Long id);
    void delete(Dayoff dayoff);
}

