package pl.edu.pjwstk.jazapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.jazapi.model.Compartment;
import pl.edu.pjwstk.jazapi.model.Train;
import pl.edu.pjwstk.jazapi.service.CompartmentService;
import pl.edu.pjwstk.jazapi.service.TrainService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/trains")
public class TrainController extends CrudController<Train> {

    CompartmentService compartmentService;
    TrainService trainService;
    public TrainController(TrainService service, CompartmentService compartmentService) {
        super(service);
        this.trainService = service;
        this.compartmentService = compartmentService;
    }

    @GetMapping("/{trainId}/compartments")
    public ResponseEntity<List<Map<String, Object>>> getAllCompartmentsForTrain(@PathVariable("trainId") Long trainId) {
            CompartmentController compartmentController = new CompartmentController(compartmentService, trainService);
            return compartmentController.getAllCompartmentsForTrain(trainId);
    }


    @GetMapping("/{trainId}/compartments/{id}")
    public ResponseEntity<Map<String, Object>> getCompartmentById(@PathVariable("trainId") Long trainId,
                                                                  @PathVariable("id") Long id) {
            CompartmentController compartmentController = new CompartmentController(compartmentService, trainService);
            return compartmentController.getCompartmentById(trainId, id);
    }

    @Override
    public Function<Train, Map<String, Object>> transformToDTO() {
        return train -> {
            var payload = new LinkedHashMap<String, Object>();
            payload.put("id", train.getId());
            payload.put("currentStation", train.getCurrentStation());
            payload.put("goingToGdansk", train.isGoingToGdansk());
            payload.put("currentPauseTime", train.getCurrentPauseTime());
            payload.put("compartmentIds", train.getCompartments().stream().map(Compartment::getId));

            return payload;
        };
    }
}
