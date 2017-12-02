package contactList.app.controller;

import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.User;
import contactList.app.service.business_trip.BusinessTripService;
import contactList.app.service.contact.ContactService;
import contactList.app.service.messageSender.mail.ApplicationMailer;
import contactList.app.service.security.SecurityService;
import contactList.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by комп on 03.12.2017.
 */
public class BusinessTripController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BusinessTripService businessTripService;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        List<BusinessTrip> businessTripList= new ArrayList<>();
        User user = userService.findByUsernameWithService(securityService.findLoggedInUsername());
        if (user != null) {
            contacts = contactService.getUserContactList(user);
        }
        model.addAttribute("contacts", contacts);
        return securityService.returnPageByCheckingOnAnonymous("welcome", "login");
    }
}
