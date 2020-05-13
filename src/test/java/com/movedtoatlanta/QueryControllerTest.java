package com.movedtoatlanta;

import com.movedtoatlanta.mocks.MockEvents;
import com.movedtoatlanta.queryapi.controllers.QueryController;
import com.movedtoatlanta.queryapi.controllers.exceptions.QueryApiAdvice;
import com.movedtoatlanta.queryapi.services.EventsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QueryControllerTest {
    @InjectMocks
    private QueryController queryController;

    @Mock
    private EventsService eventsService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(queryController)
                                 .setControllerAdvice(new QueryApiAdvice())
                                 .build();
    }

    @Test
    public void getEvents() {
        when(eventsService.getEvents(any(String.class), any(String.class)))
                .thenReturn(new MockEvents().getGoodEventList());
        try {
            mockMvc.perform(get("/api/meegs2369/github-query"))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$[0].id", is(1234567)))
                   .andExpect(jsonPath("$[0].created_at", notNullValue()))
                   .andExpect(jsonPath("$[0].type", is("PullRequestEvent")))
                   .andExpect(jsonPath("$[0].actor.id", is(1)))
                   .andExpect(jsonPath("$[0].actor.login", is("meegs2369")))
                   .andExpect(jsonPath("$[0].actor.display_login", is("meegs2369")))
                   .andExpect(jsonPath("$[0].actor.gravatar_id", is("graveeter")))
                   .andExpect(jsonPath("$[0].actor.avatar_url", is("https://www.test.com/avatar")))
                   .andExpect(jsonPath("$[0].actor.url", is("https://www.test.com")));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void getNoEvents() {
        when(eventsService.getEvents(any(String.class), any(String.class)))
                .thenReturn(new ArrayList<>());
        try {
            mockMvc.perform(get("/api/meegs2369/github-query"))
                   .andExpect(status().isBadRequest())
                   .andExpect(jsonPath("$.errorMessage", is("There were no events for the requested repository.")));
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
