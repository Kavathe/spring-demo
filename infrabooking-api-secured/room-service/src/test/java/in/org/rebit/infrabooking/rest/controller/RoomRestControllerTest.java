package in.org.rebit.infrabooking.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.org.rebit.infrabooking.entity.Room;
import in.org.rebit.infrabooking.exception.RoomNotFoundException;
import in.org.rebit.infrabooking.feign.client.RoomServiceFeignClient;
import in.org.rebit.infrabooking.service.RoomService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest //INITIALIZING SPRING CONTAINER FOR TESTING
@AutoConfigureMockMvc //to inject MockMvc object
public class RoomRestControllerTest {
    @Autowired //to test rest and web URIs
    private MockMvc mvc;

    @MockitoBean    //create a mock and register with spring container
    private RoomService service;


    @Test
    public void searchByIdTest() throws Exception, RoomNotFoundException {
        //given
        Room r = new Room();
        r.setId(1);
        r.setAvailable(true);
        r.setCapacity(12);
        r.setType("Meeting");

        Mockito.when(service.getRoomById(Mockito.anyInt())).thenReturn(r);

        //when
        //then
        mvc.perform(get("/rooms/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("type").value("Meeting"));
    }
    @Test
    public void searchByIdShouldReturn404Test() throws Exception, RoomNotFoundException {
        //given
        Mockito.when(service.getRoomById(Mockito.anyInt())).thenThrow(RoomNotFoundException.class);
        //when
        //then
        mvc.perform(get("/rooms/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));
    }



    @Disabled
    @Test
    public void demoTest() throws Exception {
        mvc.perform(get("/rooms/demo"))
                    .andExpect(status().isOk());
    }

    @Test
    public void createNewRoomTest() throws Exception {
        //given
        Room r = new Room();
        r.setId(1);
        r.setAvailable(true);
        r.setCapacity(12);
        r.setType("Meeting");

        ObjectMapper mapper = new ObjectMapper();
        String jsonRoom = mapper.writeValueAsString(r);
        System.out.println(jsonRoom);

        Mockito.when(service.registerRoom(Mockito.any(Room.class)))
               .thenReturn(r);

        //when
        //then
        mvc.perform(post("/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRoom)
                   )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("type").value("Meeting"));
    }
}
