package com.movedtoatlanta;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movedtoatlanta.network.models.Actor;
import com.movedtoatlanta.network.models.Event;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.sql.Timestamp;

public class EventTest {

    @Test
    public void getEvent() {
        Timestamp expectedTs = Timestamp.valueOf("2020-04-15 10:59:36.0");
        try {
            File file = ResourceUtils.getFile("classpath:githubevent.json");
            ObjectMapper mapper = new ObjectMapper();
            Event event = mapper.readValue(file, Event.class);
            Actor actor = event.getActor();
            Assert.assertEquals(12058735707L, (long) event.getId());
            Assert.assertTrue("meegs2369".matches(actor.getLogin()));
            Assert.assertEquals(10658319, (actor.getId().longValue()));
            Assert.assertTrue("meegs2369".matches(actor.getDisplayLogin()));
            Assert.assertEquals(expectedTs, event.getTimestamp());
            Assert.assertTrue("PullRequestEvent".matches(event.getType()));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}