package kib.lab6.client;

import kib.lab6.common.util.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ConnectionHandlerClient {

    private static final int SERVER_PORT = 1337;
    private static final int DATAGRAM_SIZE = 4096;
    private DatagramChannel datagramChannel;

    public ConnectionHandlerClient() throws IOException {
        datagramChannel = DatagramChannel.open();
        datagramChannel.bind(null);
    }

    public void sendRequest(Request request) throws IOException, ClassNotFoundException {
        InetSocketAddress isa = new InetSocketAddress("localhost", SERVER_PORT); //TODO Должен вводить юзер
        datagramChannel.send(createBuffer(request), isa);
    }

    private ByteBuffer createBuffer(Request request) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(DATAGRAM_SIZE);
        ObjectOutputStream oos = new ObjectOutputStream(bytes);
        oos.writeObject(request);
        oos.flush();
        ByteBuffer bufferToSend = ByteBuffer.wrap(bytes.toByteArray());
        oos.close();
        bytes.close();
        return bufferToSend;
    }


}
