package kib.lab6.client;

import kib.lab6.common.util.client_server_communication.ConnectionConfig;
import kib.lab6.common.util.client_server_communication.Request;
import kib.lab6.common.util.client_server_communication.Response;
import kib.lab6.common.util.client_server_communication.Serializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class ConnectionHandlerClient {

    private static final int SERVER_PORT = 1337;
    private static final int RESPONSE_TIMER = 5000;
    private final DatagramSocket datagramSocket;
    private final InetAddress serverAddress;

    public ConnectionHandlerClient(String address) throws UnknownHostException, SocketException {
        datagramSocket = new DatagramSocket();
        serverAddress = InetAddress.getByName(address);
    }

    public void sendRequest(Request request) throws IOException {
        request.setClientInfo(InetAddress.getLocalHost().toString() + ":" + datagramSocket.getLocalPort());
        ByteBuffer byteBuffer = Serializer.serializeRequest(request);
        byte[] bufferToSend = byteBuffer.array();
        DatagramPacket datagramPacket = new DatagramPacket(bufferToSend, bufferToSend.length, serverAddress, SERVER_PORT);
        datagramSocket.send(datagramPacket);
    }

    public Response recieveResponse() throws ClassNotFoundException, IOException {
        byte[] byteBuf = new byte[ConnectionConfig.getByteBufferSize()];
        DatagramPacket dpFromServer = new DatagramPacket(byteBuf, byteBuf.length);
        datagramSocket.setSoTimeout(RESPONSE_TIMER);
        datagramSocket.receive(dpFromServer);
        byte[] bytesFromServer = dpFromServer.getData();
        return Serializer.deserializeResponse(bytesFromServer);
    }

}
