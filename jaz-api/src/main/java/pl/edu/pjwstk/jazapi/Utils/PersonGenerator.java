package pl.edu.pjwstk.jazapi.Utils;


import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import pl.edu.pjwstk.jazapi.model.Compartment;
import pl.edu.pjwstk.jazapi.model.Person;
import pl.edu.pjwstk.jazapi.model.Station;

import java.util.List;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PersonGenerator {
    private static final Faker faker = new Faker(new Locale("pl-PL"));
    private static final Random generator = new Random();

    public static Person generateNewPerson(Station station, Compartment compartment) {
        Name name = faker.name();
        OptionalInt any = generator.ints(station.ordinal(), Station.values().length).findAny();
        int destinationStationOrdinal = any.orElse(station.ordinal());

        return new Person(name.firstName(), name.lastName(), Station.values()[destinationStationOrdinal], compartment);
    }

    public static List<Person> generatePeople(Station station, Compartment compartment) {
        int count = generator.ints(1, 8).findAny().orElse(0);

        return IntStream.rangeClosed(1, count)
                .mapToObj(i -> generateNewPerson(station, compartment))
                .collect(Collectors.toList());
    }
}
