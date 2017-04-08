package contactList.app.controller;

import contactList.app.action.contact.CreateContactAction;
import contactList.app.action.user.UpdateUserAction;
import contactList.app.model.Contact;
import contactList.app.model.User;
import contactList.app.service.contact.ContactService;
import contactList.app.service.security.SecurityService;
import contactList.app.service.user.UserService;
import contactList.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by комп on 05.04.2017.
 */
@Controller
public class ContactController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/add_contact", method= RequestMethod.POST)
    public String addContact(Model model,
                               @RequestParam String contactLogin,
                               @RequestParam String contactFullname,
                               @RequestParam String contactPhone,
                               @RequestParam String contactDescription,
                               @RequestParam String contactStatus,
                               @RequestParam String important){
        User user = userService.findByUsernameWithService(securityService.findLoggedInUsername());
        Contact contact = new Contact(contactLogin, contactFullname, contactPhone,
                contactDescription, contactStatus, important, user);
        new CreateContactAction(contactService, contact).execute();
        List<Contact> contactList = contactService.getUserContactList(user);
        contactList.add(contact);
        user.setUserListOfContacts(contactList);
        return "redirect:/welcome";
    }
}
