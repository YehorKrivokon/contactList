package contactList.app.model;

import javax.persistence.*;

@Entity
@Table(name="weekend")
public class Weekend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="my_entity_seq_gen")
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="HIBERNATE_SEQUENCE")
    private Long id;

    @Column(name = "time_count")
    private Integer time_count;

    @Column(name = "date")
    private String date;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Contact contact;

    public Weekend() {
    }

    public Weekend(String timeCount, String date, Contact contact) {
        this.time_count = Integer.valueOf(timeCount);
        this.date = date;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate(){
        return date;
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
