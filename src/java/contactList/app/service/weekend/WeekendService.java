package contactList.app.service.weekend;

import contactList.app.model.Contact;
import contactList.app.model.Dayoff;
import contactList.app.model.Weekend;

import java.util.List;

/**
 * Created by комп on 07.04.2017.
 */
public interface WeekendService {
    void save(Weekend weekend);
    List<Weekend> getEmployeeWeekendList(Contact contact);
    List<Weekend> getAllWeekends();
    Weekend getWeekendById(Long id);
}

