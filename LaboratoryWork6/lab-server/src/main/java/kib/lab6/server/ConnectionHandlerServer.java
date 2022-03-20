package kib.lab6.server;

import kib.lab6.common.util.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ConnectionHandlerServer {

    private static final int SERVER_PORT = 1337;
    private static final int DATAGRAM_SIZE = 4096;
    private DatagramChannel datagramChannel;

    public ConnectionHandlerServer() throws IOException {
        openPort();
    }

    private void openPort() throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", SERVER_PORT);
        datagramChannel = DatagramChannel.open();
        datagramChannel.bind(inetSocketAddress);
    }

    public Request listen() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(DATAGRAM_SIZE);
        datagramChannel.receive(buffer);
        return getRequest(buffer);
    }

    private Request getRequest (ByteBuffer buffer) throws IOException, ClassNotFoundException {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request request =  (Request) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return request;
    }

    public void closeConnection() throws IOException {
        datagramChannel.close();
    }
}
