package contactList.app.dao;

import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.Dayoff;

import java.util.List;

/**
 * Created by комп on 05.04.2017.
 */
public interface DayoffDao {
    Dayoff findByDayoffId(Long id);
    void updatDayoff(Dayoff dayoffToUpgrade);
    void add(Dayoff dayoff);
    void delete(Dayoff dayoff);
    List<Dayoff> getAllDayoffs();
    List<Dayoff> getEmployeeDayoffList(Contact contact);
}
