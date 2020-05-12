package com.movedtoatlanta.queryapi.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movedtoatlanta.network.StatusCollector;
import com.movedtoatlanta.network.models.Event;
import com.movedtoatlanta.network.models.Destination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service("eventsService")
public class EventsServiceFacade implements EventsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventsServiceFacade.class);
    @Override
    public List<Event> getEvents(String user, String repo) {
        Destination destination = new Destination(user,repo);
        StatusCollector statusCollector = new StatusCollector(destination.get());
        List<Event> events = new ArrayList<>();
        try{
            events = new ObjectMapper().readValue(statusCollector.get(), new TypeReference<List<Event>>(){});
        } catch (IOException io){
            LOGGER.error(io.getMessage());
        }
        return events;
    }
}
