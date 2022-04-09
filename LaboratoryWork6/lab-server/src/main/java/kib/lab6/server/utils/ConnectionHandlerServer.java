package kib.lab6.server.utils;

import kib.lab6.common.util.client_server_communication.ConnectionConfig;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.client_server_communication.Serializer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class ConnectionHandlerServer {

    private static final int SELECTION_TIME = 1;
    private final Selector selector;
    private final DatagramChannel datagramChannel;
    private SocketAddress socketAddress;

    public ConnectionHandlerServer() throws IOException {
        datagramChannel = DatagramChannel.open();
        selector = Selector.open();
        datagramChannel.socket().bind(new InetSocketAddress(ConnectionConfig.getServerPort()));
        datagramChannel.configureBlocking(false);
        datagramChannel.register(selector, SelectionKey.OP_READ);
    }

    public Request listen() throws ClassNotFoundException, IOException {
        if (selector.select(SELECTION_TIME) == 0) {
            return null;
        }
        Set<SelectionKey> readyKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = readyKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (key.isReadable()) {
                ByteBuffer packet = ByteBuffer.allocate(ConnectionConfig.getByteBufferSize());
                socketAddress = datagramChannel.receive(packet);
                ((Buffer) packet).flip();
                byte[] bytes = new byte[packet.remaining()];
                packet.get(bytes);
                return Serializer.deserializeRequest(bytes);
            }
        }
        return null;
    }

    public String sendResponse(Response response) throws IOException {
        ByteBuffer bufferToSend = Serializer.serializeResponse(response);
        datagramChannel.send(bufferToSend, socketAddress);
        return socketAddress.toString();
    }

    public void closeServer() throws IOException {
        datagramChannel.close();
        selector.close();
    }
}
