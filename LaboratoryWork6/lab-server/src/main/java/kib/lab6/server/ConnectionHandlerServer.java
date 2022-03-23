package kib.lab6.server;

import kib.lab6.common.util.Request;
import kib.lab6.common.util.Response;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ConnectionHandlerServer implements Closeable {

    private static final int SERVER_PORT = 1337;
    private static final int DATAGRAM_SIZE = 4096;
    private DatagramChannel datagramChannel;
    private SocketAddress clientAdress;

    public ConnectionHandlerServer() throws IOException {
        openChannels();
    }

    private void openChannels() throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", SERVER_PORT);
        datagramChannel = DatagramChannel.open();
        datagramChannel.bind(inetSocketAddress);
        //datagramChannel.configureBlocking(false);
    }

    public byte[] listen() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(DATAGRAM_SIZE);
        clientAdress = datagramChannel.receive(buffer);
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return bytes;
    }

    public void sendResponse(Response response) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(DATAGRAM_SIZE);
        datagramChannel.write(buffer);
    }

    //private ByteBuffer

    @Override
    public void close() throws IOException {
        datagramChannel.close();
    }
}
