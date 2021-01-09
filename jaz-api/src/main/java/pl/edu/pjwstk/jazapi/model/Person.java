package pl.edu.pjwstk.jazapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.edu.pjwstk.jazapi.service.DbEntity;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person implements DbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private Station destination;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Compartment compartment;

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    public void setCompartment(Compartment compartment) {
        this.compartment = compartment;
    }

    public Person(String firstName, String lastName, Station destination, Compartment compartment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.destination = destination;
        this.compartment = compartment;
    }

    public Person() {}

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Station getDestination() {
        return destination;
    }
}
