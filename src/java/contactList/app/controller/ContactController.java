package contactList.app.controller;

import contactList.app.action.contact.CreateContactAction;
import contactList.app.action.contact.DeleteContactAction;
import contactList.app.action.contact.UpdateContactAction;
import contactList.app.model.*;
import contactList.app.service.avatar.AvatarHandler;
import contactList.app.service.business_trip.BusinessTripService;
import contactList.app.service.contact.ContactService;
import contactList.app.service.dayoff.DayoffService;
import contactList.app.service.messageSender.mail.ApplicationMailer;
import contactList.app.service.security.SecurityService;
import contactList.app.service.user.UserService;
import contactList.app.service.weekend.WeekendService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    ApplicationMailer applicationMailer;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private BusinessTripService businessTripService;

    @Autowired
    private DayoffService dayoffService;

    @Autowired
    private WeekendService weekendService;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        List<Contact> contacts = new ArrayList<>();
        User user = userService.findByUsernameWithService(securityService.findLoggedInUsername());
        if (user != null) {
            contacts = contactService.getUserContactList(user);
        }

        List<BusinessTrip> businessTripList = businessTripService.getAllBusinessTrips();
        List<Weekend> weekendList = weekendService.getAllWeekends();
        List<Dayoff> dayoffFullList = dayoffService.getAllDayoff();
        model.addAttribute("businessTripList", businessTripList);
        model.addAttribute("weekendList", weekendList);
        model.addAttribute("dayoffFullList", dayoffFullList);
        model.addAttribute("contacts", contacts);
        return securityService.returnPageByCheckingOnAnonymous("welcome", "login");
    }

    @RequestMapping(value = "/showTable", method = RequestMethod.POST)
    public String showTable(Model model,
                            @RequestParam(name = "contactStatusPage1") String contactStatusPage) {
        List<Contact> contactListByDepartment = contactService.getContactsByDepartment(contactStatusPage);
        model.addAttribute("contactListByDepartment1", contactListByDepartment);
        List<Contact> contacts = new ArrayList<>();
        User user = userService.findByUsernameWithService(securityService.findLoggedInUsername());
        if (user != null) {
            contacts = contactService.getUserContactList(user);
        }
        model.addAttribute("contacts", contacts);
        model.addAttribute("contactListByDepartment2", new ArrayList<Contact>());
        return "/welcome";
    }

    @RequestMapping(value = "/showDayoff", method = RequestMethod.POST)
    public String showDayoff(Model model,
                            @RequestParam(name = "contactStatusPage2") String contactStatusPage) {
        List<Contact> contactListByDepartment = contactService.getContactsWithDayoff(contactStatusPage);
        model.addAttribute("contactListByDepartment2", contactListByDepartment);
        List<Contact> contacts = new ArrayList<>();
        User user = userService.findByUsernameWithService(securityService.findLoggedInUsername());
        if (user != null) {
            contacts = contactService.getUserContactList(user);
        }
        model.addAttribute("contacts", contacts);
        model.addAttribute("contactListByDepartment1", new ArrayList<Contact>());
        return "/welcome";
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

    @RequestMapping(value = "/deleteContact", method = RequestMethod.POST)
    public String deleteContact(Model model, @RequestParam String deletingContactId) {
        new DeleteContactAction(contactService, contactService.getContactById(Long.valueOf(deletingContactId))).execute();
        return "redirect:/welcome";
    }


    @RequestMapping("/avatar/{contact_id}")
    public ResponseEntity<byte[]> onPhoto(@PathVariable("contact_id") long id) {
        return contactService.getContactById(id).getAvatarAsAPicture();
    }

    @RequestMapping(value = "/update/{contact_id}", method = RequestMethod.POST)
    public String updateContact(@PathVariable("contact_id") long id,
                                @RequestParam String contactLoginUpd,
                                @RequestParam String contactFullnameUpd,
                                @RequestParam String contactPhoneUpd,
                                @RequestParam String contactDescriptionUpd,
                                @RequestParam String contactStatusUpd,
                                @RequestParam String importantUpd,
                                @RequestParam MultipartFile avatarUpd) {
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

    @SuppressWarnings("webapp/WEB-INF/")
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public String sendEmail() {/*
        //Create the application context
        ApplicationContext context = new FileSystemXmlApplicationContext("application-context.xml");
        ApplicationMailer mailer = (ApplicationMailer) context.getBean("mailService");
*/
        //Get the mailer instance
        applicationMailer.sendMail("eg.krivokon@gmail.com", "aaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaa");
        return "redirect:/welcome";
    }

}
