package contactList.app.service.weekend;

import contactList.app.dao.DayoffDao;
import contactList.app.dao.WeekendDao;
import contactList.app.model.Contact;
import contactList.app.model.Dayoff;
import contactList.app.model.Weekend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class WeekendServiceImpl implements WeekendService {
    @Autowired
    private WeekendDao weekendDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Weekend weekend) {
        weekendDao.add(weekend);
    }

    @Override
    public List<Weekend> getEmployeeWeekendList(Contact contact) {
        return weekendDao.getEmployeeWeekendList(contact);
    }

    @Override
    public List<Weekend> getAllWeekends() {
         return weekendDao.getAllWeekends();
    }

    @Override
    public Weekend getWeekendById(Long id) {
        return weekendDao.findByWeekendId(id);
    }
}
