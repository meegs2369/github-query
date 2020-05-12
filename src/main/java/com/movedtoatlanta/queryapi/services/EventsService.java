package com.movedtoatlanta.queryapi.services;

import com.movedtoatlanta.network.models.Event;

import java.util.List;

public interface EventsService {
    List<Event> getEvents(String user, String repo);
}
