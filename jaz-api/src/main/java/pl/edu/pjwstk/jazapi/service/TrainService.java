package pl.edu.pjwstk.jazapi.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.Train;
import pl.edu.pjwstk.jazapi.repository.TraintRepository;

import java.util.List;

@Service
public class TrainService extends CrudService<Train> {

    public TrainService(TraintRepository traintRepository) {
        super(traintRepository);
    }

    @Override
    public Train createOrUpdate(Train updateEntity) {
        return repository.save(updateEntity);
    }

    public void moveTimeForward() {
        List<Train> trains = repository.findAll();
        trains.forEach(train -> train.move());
        trains.forEach(train -> repository.save(train));
    }

}
