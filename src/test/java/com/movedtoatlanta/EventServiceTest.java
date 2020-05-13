package com.movedtoatlanta;

import com.movedtoatlanta.network.Communicator;
import com.movedtoatlanta.network.models.Event;
import com.movedtoatlanta.queryapi.services.EventsServiceFacade;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class EventServiceTest {

    @InjectMocks
    private EventsServiceFacade eventsServiceFacade;

    @Mock
    private Communicator communicator;

    private String fileString;

    @Before
    public void setUP() {
        MockitoAnnotations.initMocks(this);
        try {
            File file = ResourceUtils.getFile("classpath:githubevents.json");
            fileString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }

    }

    @Test
    public void getEvents() {
        when(communicator.communicate(anyString(), anyString())).thenReturn(fileString);
        List<Event> evtestEvents = eventsServiceFacade.getEvents("meegs2369", "service-point");
        Assert.assertEquals(14, evtestEvents.size());
    }

}
