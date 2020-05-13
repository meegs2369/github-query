package com.movedtoatlanta.network;

import java.util.regex.Pattern;

/**
 * Classes implementing this interface will have the ability to verify a path to only allow the paths that we want.
 */
public interface PathValidator {
    /**
     * Validation method
     *
     * @param path String
     * @return String
     */
    default String validate(String path) {
        String regex = Constants.BASE_URL + ".*";
        if (Pattern.matches(regex, path)) {
            return path;
        } else {
            return "";
        }
    }
}
