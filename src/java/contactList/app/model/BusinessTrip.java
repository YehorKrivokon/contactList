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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Contact contact;

    public BusinessTrip() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
