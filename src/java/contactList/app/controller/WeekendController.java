package contactList.app.controller;

import contactList.app.action.dayoff.CreateDayoffAction;
import contactList.app.action.weekend.CreateWeekendAction;
import contactList.app.model.Contact;
import contactList.app.model.Dayoff;
import contactList.app.model.Weekend;
import contactList.app.service.contact.ContactService;
import contactList.app.service.dayoff.DayoffService;
import contactList.app.service.security.SecurityService;
import contactList.app.service.weekend.WeekendService;
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
public class WeekendController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private WeekendService weekendService;

    @RequestMapping(value = {"/saveWeekend"}, method = RequestMethod.POST)
    public String saveBusinessTrip(Model model,
                             @RequestParam String weekendEmpId,
                             @RequestParam String weekendTimeCount,
                             @RequestParam String weekendDescription) {
        Contact contact = contactService.getContactById(Long.valueOf(weekendEmpId.substring(0, weekendEmpId.indexOf(" "))));
        Weekend weekend = new Weekend(weekendTimeCount, weekendDescription, contact);
        new CreateWeekendAction(weekendService, weekend).execute();
        List<Weekend> weekendList = weekendService.getEmployeeWeekendList(contact);
        weekendList.add(weekend);
        contact.setWeekendList(weekendList);
        return "redirect:/welcome";
    }

}
