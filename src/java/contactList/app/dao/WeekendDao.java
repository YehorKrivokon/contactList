package contactList.app.dao;

import contactList.app.model.Contact;
import contactList.app.model.Dayoff;
import contactList.app.model.Weekend;

import java.util.List;

/**
 * Created by комп on 05.04.2017.
 */
public interface WeekendDao {
    Weekend findByWeekendId(Long id);
    void updatWeekend(Weekend weekendToUpgrade);
    void add(Weekend weekend);
    List<Weekend> getAllWeekends();
    List<Weekend> getEmployeeWeekendList(Contact contact);
}
