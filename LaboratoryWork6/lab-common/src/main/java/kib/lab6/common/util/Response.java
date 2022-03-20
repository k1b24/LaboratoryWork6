package kib.lab6.common.util;

import kib.lab6.common.abstractions.AbstractMessage;

import java.io.Serializable;

public class Response implements Serializable {

    AbstractMessage message;

    public Response(AbstractMessage message) {
        this.message = message;
    }
}
