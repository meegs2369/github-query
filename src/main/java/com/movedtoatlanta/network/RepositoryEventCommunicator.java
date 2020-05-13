package com.movedtoatlanta.network;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * This class is an implementation of {@link com.movedtoatlanta.network.Communicator} and
 * {@link com.movedtoatlanta.network.PathValidator} that will generate response from an api.
 */
@Component
public class RepositoryEventCommunicator implements Communicator, PathValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryEventCommunicator.class);

    private String makeGet(String stringURL) {
        String[] schemes = {"http", "https"};
        String content = "";
        String validString = validate(stringURL);
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (urlValidator.isValid(stringURL)) {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpGet getRequest = new HttpGet(validString);
                LOGGER.info("making GET request");
                HttpResponse response = httpclient.execute(getRequest);
                if (response.getStatusLine()
                            .getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    content = EntityUtils.toString(entity);
                } else {
                    content = "";
                    LOGGER.error("the requested repository returned a {} status", response.getStatusLine()
                                                                                          .getStatusCode());
                }
            } catch (IOException e) {
                LOGGER.error("call failed with response: \n {}", e.getMessage());
                content = "";
            }
        }
        return content;
    }

    /* (non-Javadoc)
     * @see com.movedtoatlanta.network.Communicator#communicate(java.lang.String endpoint)
     */
    @Override
    public String communicate(String endpoint) {
        return makeGet(endpoint);
    }
}