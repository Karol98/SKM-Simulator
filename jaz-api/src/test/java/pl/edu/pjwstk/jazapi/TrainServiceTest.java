package pl.edu.pjwstk.jazapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jazapi.model.Train;
import pl.edu.pjwstk.jazapi.repository.TrainRepository;
import pl.edu.pjwstk.jazapi.service.TrainService;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainServiceTest {

    @MockBean
    TrainRepository trainRepository;

    @Autowired
    TrainService trainService;

    @Test
    public void getAllTrains() {
        trainService.getAll();
        Mockito.verify(trainRepository).findAll();
    }

    @Test
    public void getTrainByID() {
        trainService.getById(1L);
        Mockito.verify(trainRepository).findById(1L);
    }

    @Test
    public void addTrain() {
        Train train = new Train();
        Mockito.when(trainRepository.findById(1L)).thenReturn(Optional.empty());
        trainService.createOrUpdate(train);
        Mockito.verify(trainRepository).save(train);
    }

}