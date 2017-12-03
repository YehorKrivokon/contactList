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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Contact contact;

    public Dayoff() {
    }

    public Dayoff(String timeCount, String cause, Contact contact) {
        this.time_count = Integer.valueOf(timeCount);
        this.cause = cause;
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
}
