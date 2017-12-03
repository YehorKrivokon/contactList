package contactList.app.controller;

import contactList.app.action.business_trip.CreateBusinessTripAction;
import contactList.app.action.contact.CreateContactAction;
import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.User;
import contactList.app.service.avatar.AvatarHandler;
import contactList.app.service.business_trip.BusinessTripService;
import contactList.app.service.contact.ContactService;
import contactList.app.service.messageSender.mail.ApplicationMailer;
import contactList.app.service.security.SecurityService;
import contactList.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by комп on 03.12.2017.
 */
@Controller
public class BusinessTripController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BusinessTripService businessTripService;

    @RequestMapping(value = {"/saveBusinessTrip"}, method = RequestMethod.POST)
    public String saveBusinessTrip(Model model,
                             @RequestParam String businessTripEmpId,
                             @RequestParam String businessTripTimeCount,
                             @RequestParam String businessTripContributions) {
        Contact contact = contactService.getContactById(Long.valueOf(businessTripEmpId));
        BusinessTrip businessTrip = new BusinessTrip(businessTripTimeCount, businessTripContributions, contact);
        new CreateBusinessTripAction(businessTripService, businessTrip).execute();
        List<BusinessTrip> businessTripList = businessTripService.getEmployeeBusinessTripList(contact);
        businessTripList.add(businessTrip);
        contact.setBusinessTripList(businessTripList);
        return "redirect:/welcome";
    }

}
