package contactList.app.action.contact;

import contactList.app.action.Action;
import contactList.app.model.Contact;
import contactList.app.service.contact.ContactService;

/**
 * Created by комп on 07.04.2017.
 */
public abstract class ContactAction implements Action {
    protected ContactService contactService;
    protected Contact contact;

    public ContactAction(ContactService contactService, Contact contact){
        this.contact = contact;
        this.contactService = contactService;
    }
}
