package com.movedtoatlanta.network;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Supplier;


public class StatusCollector implements Supplier<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusCollector.class);
    private final String stringUrl;


    public StatusCollector(String stringURL) {
        this.stringUrl = stringURL;
    }

    private String makeGet() {
        HttpGet getRequest = null;
        String content;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            getRequest = new HttpGet(stringUrl);
            LOGGER.info("making GET request");
            HttpResponse response = client.execute(getRequest);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
        } catch (IOException e) {
            LOGGER.error("call failed with response: \n {}", e.getMessage());
            content = "";
        } finally {
            if (getRequest != null) {
                getRequest.releaseConnection();
            }
        }
        return content;
    }
    @Override
    public String get() {
        return makeGet();
    }
}
