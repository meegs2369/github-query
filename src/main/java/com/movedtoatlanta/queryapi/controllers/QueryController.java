package com.movedtoatlanta.queryapi.controllers;

import com.movedtoatlanta.network.models.Event;
import com.movedtoatlanta.queryapi.controllers.exceptions.NoRecordsFoundException;
import com.movedtoatlanta.queryapi.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class will process calls to the our api.
 */
@RestController
@RequestMapping("api")
public class QueryController {

    private final EventsService eventsService;

    @Autowired
    public QueryController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    /**
     * Method to call the service and present the results
     *
     * @param user String
     * @param repo String
     * @param type String
     * @return List
     * @throws NoRecordsFoundException upon failing to find any records.
     */
    @GetMapping("{user}/{repo}/{type}")
    @ResponseBody
    public List<Event> getEventDetails(@PathVariable String user, @PathVariable String repo, @PathVariable String type) throws NoRecordsFoundException {
        List<Event> events = eventsService.getEvents(user, repo, type);
        if (events.isEmpty()) {
            throw new NoRecordsFoundException("There were no events for the requested repository.", new NullPointerException());
        }
        return events;
    }
}