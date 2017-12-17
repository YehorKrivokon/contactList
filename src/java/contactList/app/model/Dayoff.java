package contactList.app.model;

import javax.persistence.*;

@Entity
@Table(name="dayoff")
public class Dayoff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="my_entity_seq_gen")
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="HIBERNATE_SEQUENCE")
    private Long id;

    @Column(name = "time_count")
    private Integer time_count;

    @Column(name = "cause")
    private String cause;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Contact contact;

    public Dayoff() {
    }

    public Dayoff(String timeCount, String cause, String description, Contact contact) {
        this.time_count = Integer.valueOf(timeCount);
        this.cause = cause;
        this.description = description;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCause(){
        return cause;
    }

    public void setTime_count(Integer time_count){
        this.time_count = time_count;
    }

    public Integer getTime_count(){
        return time_count;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
