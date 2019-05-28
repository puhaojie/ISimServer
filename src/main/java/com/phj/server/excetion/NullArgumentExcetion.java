package com.phj.server.excetion;

public class NullArgumentExcetion extends IllegalArgumentException {

    public NullArgumentExcetion(String reason) {
        super(reason);
    }

}
