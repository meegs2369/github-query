package com.movedtoatlanta;

import com.movedtoatlanta.network.GithubUrlCreator;
import com.movedtoatlanta.network.RepositoryEventCommunicator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


public class RepositoryEventCommunicatorTest {
    @InjectMocks
    private RepositoryEventCommunicator repositoryEventCommunicator;

    @Mock
    private GithubUrlCreator githubUrlCreator;

    @Before
    public void setUP() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getResponse() {
        when(githubUrlCreator.getURL(anyString(), anyString())).thenReturn("https://api.github.com/repos/meegs2369/service-point/events");
        String response = repositoryEventCommunicator.communicate("meegs2369", "service-point");
        Assert.assertTrue(response.contains("id"));
    }
}
