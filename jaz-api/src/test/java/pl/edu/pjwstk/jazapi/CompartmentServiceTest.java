package pl.edu.pjwstk.jazapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jazapi.model.Compartment;
import pl.edu.pjwstk.jazapi.repository.CompartmentRepository;
import pl.edu.pjwstk.jazapi.service.CompartmentService;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompartmentServiceTest {

    @MockBean
    CompartmentRepository compartmentRepository;

    @Autowired
    CompartmentService compartmentService;

    @Test
    public void getAllTrains() {
        compartmentService.getAll();
        Mockito.verify(compartmentRepository).findAll();
    }

    @Test
    public void getTrainByID() {
        compartmentService.getById(1L);
        Mockito.verify(compartmentRepository).findById(1L);
    }

    @Test
    public void addTrain() {
        Compartment compartment = new Compartment();
        Mockito.when(compartmentRepository.findById(1L)).thenReturn(Optional.empty());
        compartmentService.createOrUpdate(compartment);
        Mockito.verify(compartmentRepository).save(compartment);
    }

}