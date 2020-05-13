package com.movedtoatlanta.queryapi.controllers;

import com.movedtoatlanta.network.models.Event;
import com.movedtoatlanta.queryapi.controllers.exceptions.NoRecordsFoundException;
import com.movedtoatlanta.queryapi.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryController {

    private final EventsService eventsService;

    @Autowired
    public QueryController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping("find/{user}/{repo}")
    @ResponseBody
    public List<Event> getEventDetails(@PathVariable String user, @PathVariable String repo) throws NoRecordsFoundException {
        List<Event> events = eventsService.getEvents(user, repo);
        if (events.isEmpty()) {
            throw new NoRecordsFoundException("There were no events for the requested repository.", new NullPointerException());
        }
        return events;
    }
}