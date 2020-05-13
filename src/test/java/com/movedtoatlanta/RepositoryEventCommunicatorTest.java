package com.movedtoatlanta;

import com.movedtoatlanta.network.RepositoryEventCommunicator;
import org.junit.Assert;
import org.junit.Test;


public class RepositoryEventCommunicatorTest {
    

    @Test
    public void getResponse() {
        String response = new RepositoryEventCommunicator().communicate("https://api.github.com/repos/meegs2369/github-query/events");
        Assert.assertTrue(response.contains("id"));
    }
}
