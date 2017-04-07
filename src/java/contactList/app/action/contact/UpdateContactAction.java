package contactList.app.action.contact;

import contactList.app.model.Contact;
import contactList.app.service.contact.ContactService;

/**
 * Created by комп on 07.04.2017.
 */
public class UpdateContactAction extends ContactAction {
    public UpdateContactAction(ContactService contactService, Contact contact) {
        super(contactService, contact);
    }

    @Override
    public void execute() {
        contactService.update(contact);
    }
}
