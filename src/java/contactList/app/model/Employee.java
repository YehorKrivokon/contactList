package contactList.app.model;

import contactList.app.dao.ContactDao;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator="my_entity_seq_gen")
    @SequenceGenerator(name="my_entity_seq_gen", sequenceName="HIBERNATE_SEQUENCE")
    private Long id;

    @Column(name = "fio")
    private String fio;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany
    private List<BusinessTrip> businessTripList = new ArrayList<BusinessTrip>();

    /*@Transient
    @Autowired
    ContactDao contactDao;
*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public List<BusinessTrip> getBusinessTripList() {
        return businessTripList;
    }

    public void setBusinessTripList(List<BusinessTrip> businessTripList) {
        this.businessTripList = businessTripList;
    }
}