package contactList.app.controller;

import contactList.app.action.business_trip.CreateBusinessTripAction;
import contactList.app.action.dayoff.CreateDayoffAction;
import contactList.app.model.BusinessTrip;
import contactList.app.model.Contact;
import contactList.app.model.Dayoff;
import contactList.app.service.business_trip.BusinessTripService;
import contactList.app.service.contact.ContactService;
import contactList.app.service.dayoff.DayoffService;
import contactList.app.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by комп on 03.12.2017.
 */
@Controller
public class DayoffController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private DayoffService dayoffService;

    @RequestMapping(value = {"/saveDayoff"}, method = RequestMethod.POST)
    public String saveBusinessTrip(Model model,
                             @RequestParam String dayoffEmpId,
                             @RequestParam String dayoffTimeCount,
                             @RequestParam String dayoffDescription) {
        Contact contact = contactService.getContactById(Long.valueOf(dayoffEmpId));
        Dayoff dayoff = new Dayoff(dayoffTimeCount, dayoffDescription, contact);
        new CreateDayoffAction(dayoffService, dayoff).execute();
        List<Dayoff> dayoffList = dayoffService.getEmployeeDayoffList(contact);
        dayoffList.add(dayoff);
        contact.setDayoffList(dayoffList);
        return "redirect:/welcome";
    }

}
