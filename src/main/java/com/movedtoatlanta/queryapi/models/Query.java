package com.movedtoatlanta.queryapi.models;

/**
 * A POJO to Communicate with the form.
 */
public class Query {
    private String owner;
    private String repo;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
