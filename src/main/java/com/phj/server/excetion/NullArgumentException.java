package com.phj.server.excetion;

public class NullArgumentException extends IllegalArgumentException {

    public NullArgumentException(String reason) {
        super(reason);
    }

}
