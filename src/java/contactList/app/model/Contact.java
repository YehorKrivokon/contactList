package contactList.app.model;

import contactList.app.dao.BusinessTripDao;
import contactList.app.dao.ContactDao;
import contactList.app.service.contact.ContactService;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by комп on 05.04.2017.
 */
@Entity
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="my_entity_seq_gen")
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="HIBERNATE_SEQUENCE")
    private Long id;

    @Column(name = "contactlogin")
    private String contactLogin;

    @Column(name = "contactfullname")
    private String contactFullname;

    @Column(name = "contactphone")
    private String contactPhone;

    @Column(name = "contactdescription")
    private String contactDescription;

    @Column(name = "contactimportance")
    private String contactImportance;

    @Column(name = "contactstatus")
    private String contactStatus;

    @Column(name = "avatar")
    private Byte[] avatar;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact", cascade = CascadeType.ALL)
    private List<BusinessTrip> businessTripList = new ArrayList<BusinessTrip>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact", cascade = CascadeType.ALL)
    private List<Dayoff> dayoffList = new ArrayList<Dayoff>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact", cascade = CascadeType.ALL)
    private List<Weekend> weekendList = new ArrayList<Weekend>();

    public Integer getAllBusinessTrip() {
        Integer a = 0;
        for (BusinessTrip bt: businessTripList) {
            a += bt.getTime_count();
        }
        return a;
    }

    public Float getAllBusinessTripContr() {
        Float a = 0f;
        for (BusinessTrip bt: businessTripList) {
            a += bt.getContributions();
        }
        return a;
    }

    public int getAllDayoffs() {
        int a = 0;
        for (Dayoff d: dayoffList) {
            a += d.getTime_count();
        }
        return a;
    }

    public int getAllWeekends() {
        int a = 0;
        for (Weekend w: weekendList) {
            a += w.getTime_count();
        }
        return a;
    }

    public Contact() {
    }

    public Contact(String contactLogin, String contactFullname, String contactPhone, String contactDescription, String contactStatus, String important, Byte[] avatar, User user) {
        this.contactLogin = contactLogin;
        this.contactFullname = contactFullname;
        this.contactPhone = contactPhone;
        this.contactDescription = contactDescription;
        this.contactStatus = contactStatus;
        this.contactImportance = important;
        this.avatar = avatar;
        this.user = user;
    }

    public void setDayoffList(List<Dayoff> dayoffList) {
        this.dayoffList = dayoffList;
    }

    public List<Dayoff> getDayoffList() {
        return dayoffList;
    }

    public List<Weekend> getWeekendList() {return weekendList; }

    public void setWeekendList(List<Weekend> weekendList) {
        this.weekendList = weekendList;
    }

    public void setBusinessTripList(List<BusinessTrip> businessTripList) {
        this.businessTripList = businessTripList;
    }

    public List<BusinessTrip> getBusinessTripList() {
        return businessTripList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactLogin() {
        return contactLogin;
    }

    public void setContactLogin(String contactLogin) {
        this.contactLogin = contactLogin;
    }

    public String getContactFullname() {
        return contactFullname;
    }

    public void setContactFullname(String contactFullname) {
        this.contactFullname = contactFullname;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactDescription() {
        return contactDescription;
    }

    public void setContactDescription(String contactDescription) {
        this.contactDescription = contactDescription;
    }

    public String getContactImportance() {
        return contactImportance;
    }

    public void setContactImportance(String contactImportance) {
        this.contactImportance = contactImportance;
    }

    public String getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(String contactStatus) {
        this.contactStatus = contactStatus;
    }

    public Byte[] getAvatar() {
        return avatar;
    }

    @Transient
    public ResponseEntity<byte[]> getAvatarAsAPicture(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<byte[]>(ArrayUtils.toPrimitive(avatar), headers, HttpStatus.OK);
    }

    public void setAvatar(Byte[] avatar) {
        this.avatar = avatar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
