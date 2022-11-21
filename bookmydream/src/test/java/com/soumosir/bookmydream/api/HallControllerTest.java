package com.soumosir.bookmydream.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soumosir.bookmydream.model.Hall;
import com.soumosir.bookmydream.service.HallService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {HallController.class})
@ExtendWith(SpringExtension.class)
class HallControllerTest {
    @Autowired
    private HallController hallController;

    @MockBean
    private HallService hallService;

    /**
     * Method under test: {@link HallController#getHalls()}
     */
    @Test
    void testGetHalls() throws Exception {
        when(this.hallService.getHalls()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/hall");
        MockMvcBuilders.standaloneSetup(this.hallController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link HallController#getHalls()}
     */
    @Test
    void testGetHalls2() throws Exception {
        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("?");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);

        ArrayList<Hall> hallList = new ArrayList<>();
        hallList.add(hall);
        when(this.hallService.getHalls()).thenReturn(hallList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/hall");
        MockMvcBuilders.standaloneSetup(this.hallController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("[{\"id\":123,\"name\":\"?\",\"totalRows\":1,\"totalColumns\":1}]"));
    }

    /**
     * Method under test: {@link HallController#getHalls()}
     */
    @Test
    void testGetHalls3() throws Exception {
        when(this.hallService.getHalls()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/hall");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.hallController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link HallController#getHalls()}
     */
    @Test
    void testGetHalls4() throws Exception {
        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("?");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("?");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);

        ArrayList<Hall> hallList = new ArrayList<>();
        hallList.add(hall1);
        hallList.add(hall);
        when(this.hallService.getHalls()).thenReturn(hallList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/hall");
        MockMvcBuilders.standaloneSetup(this.hallController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"name\":\"?\",\"totalRows\":1,\"totalColumns\":1},{\"id\":123,\"name\":\"?\",\"totalRows\":1,\"totalColumns"
                                        + "\":1}]"));
    }

    /**
     * Method under test: {@link HallController#saveHall(Hall)}
     */
    @Test
    void testSaveHall() throws Exception {
        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        when(this.hallService.saveHall((Hall) any())).thenReturn(hall);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        String content = (new ObjectMapper()).writeValueAsString(hall1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/hall")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.hallController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":123,\"name\":\"Name\",\"totalRows\":1,\"totalColumns\":1}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/hall"));
    }

    /**
     * Method under test: {@link HallController#updateHall(long, Hall)}
     */
    @Test
    void testUpdateHall() throws Exception {
        Hall hall = new Hall();
        hall.setId(123L);
        hall.setName("Name");
        hall.setSeats(new ArrayList<>());
        hall.setTotalColumns(1L);
        hall.setTotalRows(1L);
        when(this.hallService.updateHall((Long) any(), (Hall) any())).thenReturn(hall);

        Hall hall1 = new Hall();
        hall1.setId(123L);
        hall1.setName("Name");
        hall1.setSeats(new ArrayList<>());
        hall1.setTotalColumns(1L);
        hall1.setTotalRows(1L);
        String content = (new ObjectMapper()).writeValueAsString(hall1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/hall/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.hallController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":123,\"name\":\"Name\",\"totalRows\":1,\"totalColumns\":1}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/hall/123"));
    }

    /**
     * Method under test: {@link HallController#deleteHall(long)}
     */
    @Test
    void testDeleteHall() throws Exception {
        doNothing().when(this.hallService).deleteHall((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/hall/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.hallController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link HallController#deleteHall(long)}
     */
    @Test
    void testDeleteHall2() throws Exception {
        doNothing().when(this.hallService).deleteHall((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/hall/{id}", 123L);
        deleteResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.hallController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

