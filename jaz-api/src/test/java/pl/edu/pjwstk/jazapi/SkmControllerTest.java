package pl.edu.pjwstk.jazapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.edu.pjwstk.jazapi.controller.SkmController;
import pl.edu.pjwstk.jazapi.service.SkmService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SkmController.class)
public class SkmControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SkmController skmController;

    @MockBean
    SkmService skmService;

    @Test
    public void moveTest() throws Exception {
        mockMvc.perform(get("/skm/go")).andExpect(status().isAccepted());
    }

}