package com.movedtoatlanta.network;

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

@Component
public class RepositoryEventCommunicator implements Communicator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryEventCommunicator.class);
    private final GithubUrlCreator githubUrlCreator;

    public RepositoryEventCommunicator(GithubUrlCreator githubUrlCreator) {
        this.githubUrlCreator = githubUrlCreator;
    }

    private String makeGet(String user, String repo) {
        String content;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet getRequest = new HttpGet(githubUrlCreator.getURL(user, repo));
            LOGGER.info("making GET request");
            HttpResponse response = httpclient.execute(getRequest);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
        } catch (IOException e) {
            LOGGER.error("call failed with response: \n {}", e.getMessage());
            content = "";
        }
        return content;
    }


    @Override
    public String communicate(String user, String repo) {
        return makeGet(user, repo);
    }
}