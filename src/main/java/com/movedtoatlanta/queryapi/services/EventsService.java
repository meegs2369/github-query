package com.movedtoatlanta.queryapi.services;

import com.movedtoatlanta.network.models.Event;

import java.util.List;

/**
 * Classes implementing this will produce a List of {@link com.movedtoatlanta.network.models.Event}
 */
public interface EventsService {
    /**
     * Method to produce the List
     *
     * @param user String
     * @param repo String
     * @param type String
     * @return List
     */
    List<Event> getEvents(String user, String repo, String type);
}
