package com.movedtoatlanta.queryapi.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movedtoatlanta.network.GithubUrlCreator;
import com.movedtoatlanta.network.RepositoryEventCommunicator;
import com.movedtoatlanta.network.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Customized implementation of {@link com.movedtoatlanta.queryapi.services.EventsService}
 */
@Service("eventsService")
public class EventsServiceFacade implements EventsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventsServiceFacade.class);
    private final RepositoryEventCommunicator repositoryEventCommunicator;
    private final GithubUrlCreator githubUrlCreator;

    @Autowired
    public EventsServiceFacade(RepositoryEventCommunicator repositoryEventCommunicator, GithubUrlCreator githubUrlCreator) {
        this.repositoryEventCommunicator = repositoryEventCommunicator;
        this.githubUrlCreator = githubUrlCreator;
    }

    /* (non-Javadoc)
     * @see com.movedtoatlanta.queryapi.services.EventsService#getEvents(java.lang.String user, java.lang.String repo,  java.lang.String type)
     */
    @Override
    public List<Event> getEvents(String user, String repo, String type) {
        List<Event> events = new ArrayList<>();
        String url = githubUrlCreator.getURI(user, repo);
        String stringResponse = repositoryEventCommunicator.communicate(url);
        if (!stringResponse.isEmpty()) {
            try {
                events = new ObjectMapper().readValue(stringResponse, new TypeReference<List<Event>>() {
                });
            } catch (IOException io) {
                LOGGER.error(io.getMessage());
            }
        }
        events.removeIf(e -> !type.equals(e.getType()));
        return events;
    }
}
