package com.movedtoatlanta;

import com.movedtoatlanta.network.GithubUrlCreator;
import org.junit.Assert;
import org.junit.Test;

public class GitHubUrlCreatorTest {
    @Test
    public void getURI() {
        String uri = new GithubUrlCreator().getURI("meegs2369", "github-query");
        Assert.assertTrue("https://api.github.com/repos/meegs2369/github-query/events".matches(uri));
    }
}
