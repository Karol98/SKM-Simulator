package pl.edu.pjwstk.jazapi.model;


import pl.edu.pjwstk.jazapi.Utils.PersonGenerator;
import pl.edu.pjwstk.jazapi.service.DbEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trains")
public class Train implements DbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compartment> compartments;

    private Station currentStation;
    private boolean goingToGdansk;


    private int currentPauseTime;


    public Train() {}

    public void setId(Long id) {
        this.id = id;
    }

    public List<Compartment> getCompartments() {
        return compartments;
    }

    public void setCompartments(List<Compartment> compartments) {
        this.compartments = compartments;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }

    public boolean isGoingToGdansk() {
        return goingToGdansk;
    }

    public void setGoingToGdansk(boolean goingToGdansk) {
        this.goingToGdansk = goingToGdansk;
    }

    public int getCurrentPauseTime() {
        return currentPauseTime;
    }

    public void setCurrentPauseTime(int currentPauseTime) {
        this.currentPauseTime = currentPauseTime;
    }

    public void move() {
        if (currentPauseTime > 0) {
            currentPauseTime--;
        } else {
            int nextStationModifier = goingToGdansk ? 1 : -1;
            currentStation = Station.values()[currentStation.ordinal() + nextStationModifier];
            currentPauseTime = currentStation.getPauseTime();

            if (currentStation.getPauseTime() > 0) {
                goingToGdansk = !goingToGdansk;
            }

            compartments.forEach(c -> c.disembark(this.currentStation, c));
            compartments.forEach(c -> {
                List<Person> people = PersonGenerator.generatePeople(this.currentStation, c);
                people.forEach(c::embark);
            });
        }
    }

    @Override
    public long getId() {
        return id;
    }
}
