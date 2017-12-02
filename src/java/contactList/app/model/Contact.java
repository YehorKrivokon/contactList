package contactList.app.model;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany
    private List<BusinessTrip> businessTripList = new ArrayList<BusinessTrip>();

    public Contact() {
    }

    public Contact(String contactLogin, String contactFullname, String contactPhone, String contactDescription, String contactStatus, String important, Byte[] avatar, User user) {
        this.contactLogin = contactLogin;
        this.contactFullname = contactFullname;
        this.contactPhone = contactPhone;
        this. contactDescription = contactDescription;
        this.contactStatus = contactStatus;
        this.contactImportance = important;
        this.avatar = avatar;
        this.user = user;
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
