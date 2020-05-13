package com.movedtoatlanta.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This class is an implementation of {@link com.movedtoatlanta.network.UriComposer} and
 * {@link com.movedtoatlanta.network.PathValidator} that will generate a url for the desired github api.
 */
@Component
public class GithubUrlCreator implements UriComposer, PathValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(GithubUrlCreator.class);
    private static final String BASE_URL = Constants.BASE_URL;

    /* (non-Javadoc)
     * @see com.movedtoatlanta.network.UriComposer#getURI(java.lang.String firstString, java.lang.String secondString)
     */
    @Override
    public String getURI(String owner, String repo) {
        String requestDestination = BASE_URL + owner + "/" + repo + "/events";
        requestDestination = validate(requestDestination);
        LOGGER.info("selected url: \n{}\n", requestDestination);
        return requestDestination;
    }
}
