package pl.edu.pjwstk.jazapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.pjwstk.jazapi.controller.CrudController;
import pl.edu.pjwstk.jazapi.controller.TrainController;
import pl.edu.pjwstk.jazapi.service.CompartmentService;
import pl.edu.pjwstk.jazapi.service.TrainService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CrudController.class)
public class TrainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TrainController trainController;

    @MockBean
    TrainService trainService;

    @MockBean
    CompartmentService compartmentService;

    @Test
    public void getAllTrainsTest() throws Exception {
        mockMvc.perform(get("/trains")).andExpect(status().isOk());
    }

    @Test
    public void getTrainByIdTest() throws Exception {
        mockMvc.perform(get("/trains/3/")).andExpect(status().isOk());
    }

    @Test
    public void getCompartmentsFromTrainTest() throws Exception {
        mockMvc.perform(get("/trains/1/compartments")).andExpect(status().isOk());
    }

    @Test
    public void getCompartmentByIdTest() throws Exception {
        mockMvc.perform(get("/trains/1/compartments/1")).andExpect(status().isOk());
    }
}
