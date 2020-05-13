package com.movedtoatlanta.network;

/**
 * Classes implementing this interface will generate a String representing the location of a resource
 */
public interface UriComposer {
    /**
     * Method that generates the representation.
     *
     * @param firstString  String
     * @param secondString String
     * @return String
     */
    String getURI(String firstString, String secondString);
}
