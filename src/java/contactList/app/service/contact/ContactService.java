package contactList.app.service.contact;

import contactList.app.model.Contact;
import contactList.app.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by комп on 07.04.2017.
 */
public interface ContactService {
    void save(Contact cntact);
    void update(Contact contact);
    List<Contact> getUserContactList(User user);
    Contact getContactById(Long id);
    void delete(Contact contact);
}

