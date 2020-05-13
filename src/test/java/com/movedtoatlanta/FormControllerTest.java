package com.movedtoatlanta;

import com.movedtoatlanta.mocks.MockEvents;
import com.movedtoatlanta.queryapi.controllers.FormController;
import com.movedtoatlanta.queryapi.controllers.QueryController;
import com.movedtoatlanta.queryapi.controllers.exceptions.NoRecordsFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FormControllerTest {
    @InjectMocks
    private FormController formController;

    @Mock
    private QueryController queryController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(formController)
                                 .build();
    }

    @Test
    public void getForm() {
        try {
            mockMvc.perform(get("/query"))
                   .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void getEvents() {
        try {
            when(queryController.getEventDetails(any(String.class), any(String.class), any(String.class)))
                    .thenReturn(new MockEvents().getGoodEventList());
            mockMvc.perform(post("/query"))
                   .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void getNoEvents() {
        try {
            when(queryController.getEventDetails(any(String.class), any(String.class), any(String.class)))
                    .thenThrow(new NoRecordsFoundException("", new NullPointerException()));
            mockMvc.perform(post("/query"))
                   .andExpect(status().isOk());
        } catch (Exception e) {
            Assert.fail();
        }
    }
}