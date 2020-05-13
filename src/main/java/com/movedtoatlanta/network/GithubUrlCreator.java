package com.movedtoatlanta.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GithubUrlCreator implements UrlCreator {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubUrlCreator.class);
    private static final String BASE_URL = "https://api.github.com/repos/";


    @Override
    public String getURL(String owner, String repo) {
        String requestDestination = BASE_URL + owner + "/" + repo + "/events";
        LOGGER.info("selected url: \n{}\n", requestDestination);
        return requestDestination;
    }
}
