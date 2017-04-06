package contactList.app.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;


/**
 * Created by комп on 05.04.2017.
 */
@Entity
@Table(name="contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="my_entity_seq_gen")
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="HIBERNATE_SEQUENCE")
    private Long id;

    @Column(name = "contactlogin")
    private String contactLogin;

    @Column(name = "contactfullname")
    private String contactFullname;

    @Column(name = "contactphone")
    private Integer contactPhone;

    @Column(name = "contactdescription")
    private String contactDescription;

    @Column(name = "contactimportance")
    private String contactImportance;

    @Column(name = "contactstatus")
    private String contactstatus;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    public Contact() {
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

    public Integer getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(Integer contactPhone) {
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

    public String getContactstatus() {
        return contactstatus;
    }

    public void setContactstatus(String contactstatus) {
        this.contactstatus = contactstatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
