package com.movedtoatlanta;

import com.movedtoatlanta.network.GithubUrlCreator;
import com.movedtoatlanta.network.RepositoryEventCommunicator;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EventServiceTest {

    @InjectMocks
    private EventsServiceFacade eventsServiceFacade;

    @Mock
    private RepositoryEventCommunicator repositoryEventCommunicator;

    @Mock
    private GithubUrlCreator githubUrlCreator;

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
        when(githubUrlCreator.getURI(any(String.class), any(String.class))).thenReturn("https://api.github.com/repos/meegs2369/github-query/events");
        when(repositoryEventCommunicator.communicate(any(String.class))).thenReturn(fileString);
        List<Event> evtestEvents = eventsServiceFacade.getEvents("meegs2369", "service-point", "PullRequestEvent");
        Assert.assertEquals(4, evtestEvents.size());
    }

}
