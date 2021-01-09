package pl.edu.pjwstk.jazapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pjwstk.jazapi.model.Compartment;
import pl.edu.pjwstk.jazapi.service.CompartmentService;
import pl.edu.pjwstk.jazapi.service.TrainService;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/Compartment")
public class CompartmentController extends CrudController<Compartment> {

    TrainService trainService;

    public CompartmentController(CompartmentService service,  TrainService trainService) {
        super(service);
        this.trainService = trainService;
    }

    public ResponseEntity<List<Map<String, Object>>> getAllCompartmentsForTrain(Long trainId) {
        try {
            Collection<Compartment> all = trainService.getById(trainId).getCompartments();
            List<Map<String, Object>> payload = all.stream()
                    .map(obj -> transformToDTO().apply(obj))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getCompartmentById(Long trainId, Long id) {
        try {
            Compartment compartment = trainService.getById(trainId).getCompartments().stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElse(null);
            Map<String, Object> payload = transformToDTO().apply(compartment);

            return new ResponseEntity<>(payload, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public Function<Compartment, Map<String, Object>> transformToDTO() {
        return compartment -> {
            var payload = new LinkedHashMap<String, Object>();
            payload.put("id", compartment.getId());
            payload.put("capacity", compartment.getCapacity());
            payload.put("spaceUsed", compartment.getOccupants().size());
            payload.put("occupants", compartment.getOccupants());

            return payload;
        };
    }
}
