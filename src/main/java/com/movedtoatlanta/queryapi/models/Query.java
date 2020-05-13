package com.movedtoatlanta.queryapi.models;

/**
 * A POJO to Communicate with the form.
 */
public class Query {
    private String owner;
    private String repo;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }
}
