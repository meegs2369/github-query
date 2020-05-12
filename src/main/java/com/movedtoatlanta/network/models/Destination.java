package com.movedtoatlanta.network.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class Destination implements Supplier<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Destination.class);
    private static final String BASE_URL = "https://api.github.com/repos/";
    private final String owner;
    private final String repo;

    public Destination(String owner, String repo){
        this.owner = owner;
        this.repo = repo;
    }

    @Override
    public String get() {
        String requestDestination = BASE_URL+ owner +"/"+repo+"/events";
        LOGGER.info("selected url: \n{}\n",requestDestination);
        return requestDestination;
    }
}
