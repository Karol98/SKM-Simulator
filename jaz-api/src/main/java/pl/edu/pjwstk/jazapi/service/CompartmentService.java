package pl.edu.pjwstk.jazapi.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.jazapi.model.Compartment;
import pl.edu.pjwstk.jazapi.repository.CompartmentRepository;


@Service
public class CompartmentService extends CrudService<Compartment> {


    public CompartmentService(CompartmentRepository repository) {
        super(repository);
    }

    @Override
    public Compartment createOrUpdate(Compartment updateEntity) {
        return repository.save(updateEntity);
    }

}
