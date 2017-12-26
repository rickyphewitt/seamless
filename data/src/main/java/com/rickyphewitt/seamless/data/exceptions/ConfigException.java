package com.rickyphewitt.seamless.data.exceptions;

public class ConfigException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ConfigException(String issue) {
        super(issue);
    }

    public ConfigException(String issue, Exception e) {
        super(issue, e);
    }
}
