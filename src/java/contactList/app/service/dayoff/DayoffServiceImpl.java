package contactList.app.service.dayoff;

import contactList.app.dao.BusinessTripDao;
import contactList.app.dao.DayoffDao;
import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.Dayoff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class DayoffServiceImpl implements DayoffService {
    @Autowired
    private DayoffDao dayoffDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Dayoff getDayoffById(Long id) {
        return dayoffDao.findByDayoffId(id);
    }

    @Override
    public void save(Dayoff dayoff) {
        dayoffDao.add(dayoff);
    }

    @Override
    public List<Dayoff> getEmployeeDayoffList(Contact contact) {
        return dayoffDao.getEmployeeDayoffList(contact);
    }

    @Override
    public List<Dayoff> getAllDayoff() {
        return dayoffDao.getAllDayoffs();
    }

    @Override
    public void delete(Dayoff dayoff) {
        dayoffDao.delete(dayoff);
    }
}
