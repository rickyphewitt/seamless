package com.rickyphewitt.seamless.data.exceptions;

public class ConfigNotFoundException extends ConfigException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ConfigNotFoundException(String issue) {
        super(issue);
    }

    public ConfigNotFoundException(String issue, Exception e) {
        super(issue, e);
    }
}
