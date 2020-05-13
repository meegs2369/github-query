package com.movedtoatlanta.mocks;

import com.movedtoatlanta.network.models.Actor;
import com.movedtoatlanta.network.models.Event;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MockEvents {
    public List<Event> getGoodEventList() {
        List<Event> events = new ArrayList<>();
        Timestamp eventTimestamp = Timestamp.valueOf("2020-04-15 10:59:36.0");
        Event event = new Event();
        Actor actor = new Actor();
        actor.setId(1L);
        actor.setLogin("meegs2369");
        actor.setAvatarUrl("https://www.test.com/avatar");
        actor.setDisplayLogin("meegs2369");
        actor.setGravatarId("graveeter");
        actor.setUrl("https://www.test.com");
        event.setActor(actor);
        event.setType("PullRequestEvent");
        event.setId(1234567L);
        event.setTimestamp(eventTimestamp);
        events.add(event);
        return events;
    }
}
