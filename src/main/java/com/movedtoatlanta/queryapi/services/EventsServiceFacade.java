package com.movedtoatlanta.queryapi.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movedtoatlanta.network.RepositoryEventCommunicator;
import com.movedtoatlanta.network.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("eventsService")
public class EventsServiceFacade implements EventsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventsServiceFacade.class);
    private final RepositoryEventCommunicator communicator;

    public EventsServiceFacade(RepositoryEventCommunicator communicator) {
        this.communicator = communicator;
    }

    @Override
    public List<Event> getEvents(String user, String repo) {

        List<Event> events = new ArrayList<>();
        String stringResponse = communicator.communicate(user, repo);
        try {
            events = new ObjectMapper().readValue(stringResponse, new TypeReference<List<Event>>() {
            });
        } catch (IOException io) {
            LOGGER.error(io.getMessage());
        }
        return events;
    }
}
