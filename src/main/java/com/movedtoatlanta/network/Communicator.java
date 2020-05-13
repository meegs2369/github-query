package com.movedtoatlanta.network;

/**
 * Classes implementing this will have take a String parameter and produce a String of a response.
 */
public interface Communicator {
    /**
     * Method to produce the response
     *
     * @param endpoint String
     * @return String
     */
    String communicate(String endpoint);
}
