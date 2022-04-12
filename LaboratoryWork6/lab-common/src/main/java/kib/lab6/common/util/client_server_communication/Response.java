package kib.lab6.common.util.client_server_communication;

import kib.lab6.common.abstractions.AbstractMessage;
import kib.lab6.common.entities.HumanBeing;

import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {

    private final AbstractMessage message;
    private final ArrayList<HumanBeing> people;

    public Response(AbstractMessage message) {
        this.message = message;
        this.people = null;
    }

    public Response(AbstractMessage message, ArrayList<HumanBeing> people) {
        this.message = message;
        this.people = people;
    }

    public AbstractMessage getMessage() {
        return message;
    }

    public ArrayList<HumanBeing> getPeople() {
        return people;
    }
}
