package contactList.app.controller;

import contactList.app.action.contact.CreateContactAction;
import contactList.app.action.contact.DeleteContactAction;
import contactList.app.action.contact.UpdateContactAction;
import contactList.app.action.user.UpdateUserAction;
import contactList.app.model.Contact;
import contactList.app.model.User;
import contactList.app.service.Avatar.AvatarHandler;
import contactList.app.service.contact.ContactService;
import contactList.app.service.messageSender.mail.MailSending;
import contactList.app.service.security.SecurityService;
import contactList.app.service.user.UserService;
import contactList.app.validator.UserValidator;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        List<Contact> contacts = new ArrayList<>();
        User user = userService.findByUsernameWithService(securityService.findLoggedInUsername());
        if (user != null) {
            contacts = contactService.getUserContactList(user);
        }
        model.addAttribute("contacts", contacts);
        return securityService.returnPageByCheckingOnAnonymous("welcome", "login");
    }

    @RequestMapping(value = "/add_contact", method = RequestMethod.POST)
    public String addContact(Model model,
                             @RequestParam String contactLogin,
                             @RequestParam String contactFullname,
                             @RequestParam String contactPhone,
                             @RequestParam String contactDescription,
                             @RequestParam String contactStatus,
                             @RequestParam String important,
                             @RequestParam MultipartFile avatar) {
        User user = userService.findByUsernameWithService(securityService.findLoggedInUsername());
        Contact contact = new Contact(contactLogin, contactFullname, contactPhone,
                contactDescription, contactStatus, important, AvatarHandler.getBytesFromPhoto(avatar), user);
        new CreateContactAction(contactService, contact).execute();
        List<Contact> contactList = contactService.getUserContactList(user);
        contactList.add(contact);
        user.setUserListOfContacts(contactList);
        return "redirect:/welcome";
    }

    @RequestMapping(value="/deleteContact", method = RequestMethod.POST)
    public String deleteContact(Model model, @RequestParam String deletingContactId){
        new DeleteContactAction(contactService, contactService.getContactById(Long.valueOf(deletingContactId))).execute();
        return "redirect:/welcome";
    }


    @RequestMapping("/avatar/{contact_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("contact_id") long id) {
        return contactService.getContactById(id).getAvatarAsAPicture();
    }

    @RequestMapping(value="/update/{contact_id}", method = RequestMethod.POST)
    public String updateContact(@PathVariable("contact_id") long id,
                                @RequestParam String contactLoginUpd,
                                @RequestParam String contactFullnameUpd,
                                @RequestParam String contactPhoneUpd,
                                @RequestParam String contactDescriptionUpd,
                                @RequestParam String contactStatusUpd,
                                @RequestParam String importantUpd,
                                @RequestParam MultipartFile avatarUpd){
        Contact contact = contactService.getContactById(id);
        try {
            contact.setAvatar(ArrayUtils.toObject(avatarUpd.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        contact.setContactLogin(contactLoginUpd);
        contact.setContactFullname(contactFullnameUpd);
        contact.setContactPhone(contactPhoneUpd);
        contact.setContactDescription(contactDescriptionUpd);
        contact.setContactImportance(importantUpd);
        contact.setContactStatus(contactStatusUpd);
        new UpdateContactAction(contactService, contact).execute();
        return "redirect:/welcome";
    }

    @RequestMapping(value="/sendEmail", method = RequestMethod.POST)
    public String sendEmail(){
        new MailSending().placeOrder();

        return "redirect:/welcome";
    }

}
