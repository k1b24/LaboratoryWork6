package kib.lab6.server;

import kib.lab6.common.util.ConnectionConfig;
import kib.lab6.common.util.Request;
import kib.lab6.common.util.Response;
import kib.lab6.common.util.Serializer;

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

    private Selector selector;
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;

    public ConnectionHandlerServer() throws IOException {
        datagramChannel = DatagramChannel.open();
        selector = Selector.open();
        datagramChannel.socket().bind(new InetSocketAddress(ConnectionConfig.getServerPort()));
        datagramChannel.configureBlocking(false);
        datagramChannel.register(selector, SelectionKey.OP_READ);
    }

    public Request listen() throws ClassNotFoundException, IOException {
        selector.select(); //убрал проверку на количество готовых каналов (видимо она не имеет смысла но не факт, надо тестить саму прогу)
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
                Request request = Serializer.deserializeRequest(bytes);
                System.out.println(socketAddress);
                return request;
            }
        }
        return null;
    }

    public void sendResponse(Response response) throws IOException {
        ByteBuffer bufferToSend = Serializer.serializeResponse(response);
        datagramChannel.send(bufferToSend, socketAddress);
    }
}
