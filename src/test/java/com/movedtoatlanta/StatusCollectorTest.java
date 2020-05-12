package com.movedtoatlanta;

import com.movedtoatlanta.network.models.Destination;
import com.movedtoatlanta.network.StatusCollector;
import org.junit.Assert;
import org.junit.Test;


public class StatusCollectorTest {
    @Test
    public void getResponse() {
        Destination destination = new Destination("meegs2369", "service-point");
        StatusCollector statusCollector = new StatusCollector(destination.get());
        String response = statusCollector.get();
        Assert.assertTrue(response.contains("id"));
    }
}
