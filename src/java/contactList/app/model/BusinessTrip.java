package contactList.app.model;

import javax.persistence.*;

@Entity
@Table(name="business_trip")
public class BusinessTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="my_entity_seq_gen")
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="HIBERNATE_SEQUENCE")
    private Long id;

    @Column(name = "time_count")
    private Integer time_count;

    @Column(name = "contributions")
    private Float contributions;

    @Column(name="date")
    private String date;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Contact contact;

    public BusinessTrip() {
    }

    public BusinessTrip(String businessTripTimeCount, String date, String businessTripContributions, Contact contact) {
        this.time_count = Integer.valueOf(businessTripTimeCount);
        this.date = date;
        this.contributions = Float.valueOf(businessTripContributions);
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setContributions(Float contributions) {
        this.contributions = contributions;
    }

    public Float getContributions(){
        return contributions;
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
