package pl.edu.pjwstk.jazapi.service;

import org.springframework.stereotype.Service;

@Service
public class SkmService {
    TrainService trainService;

    public SkmService(TrainService trainService) {
        this.trainService = trainService;
    }

    public void moveTimeForward() {
        trainService.moveTimeForward();
    }
}
