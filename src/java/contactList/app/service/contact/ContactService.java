package contactList.app.service.contact;

import contactList.app.model.Contact;

/**
 * Created by комп on 07.04.2017.
 */
public interface ContactService {
    void save(Contact cntact);
    void update(Contact contact);
}

