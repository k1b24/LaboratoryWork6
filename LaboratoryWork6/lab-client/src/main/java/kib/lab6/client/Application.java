package kib.lab6.client;

import kib.lab6.common.util.Request;

import java.io.IOException;

public class Application {

    public Application() {

    }

    public void launchApplication() {
        Request request1 = new Request("show", "");
        Request request2 = new Request("info", "");
        try {
            ConnectionHandlerClient connectionHandlerClient = new ConnectionHandlerClient();
            connectionHandlerClient.sendRequest(request1);
            connectionHandlerClient.sendRequest(request2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
