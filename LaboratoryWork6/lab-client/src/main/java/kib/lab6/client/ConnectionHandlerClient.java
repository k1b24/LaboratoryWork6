package kib.lab6.client;

import kib.lab6.common.util.Request;
import kib.lab6.common.util.Response;
import kib.lab6.common.util.Serializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class ConnectionHandlerClient {

    private static final int SERVER_PORT = 1337;
    DatagramSocket datagramSocket;
    InetAddress serverAddress;

    public ConnectionHandlerClient(String address) throws UnknownHostException, SocketException {
        datagramSocket = new DatagramSocket();
        serverAddress = InetAddress.getByName(address);
    }

    public void sendRequest(Request request) throws IOException {
        ByteBuffer byteBuffer = Serializer.serializeRequest(request);
        byte[] bufferToSend = byteBuffer.array();
        DatagramPacket datagramPacket = new DatagramPacket(bufferToSend, bufferToSend.length, serverAddress, SERVER_PORT);
        datagramSocket.send(datagramPacket);
    }

    public Response recieveResponse() throws ClassNotFoundException, IOException {
        byte[] byteBuf = new byte[4096];
        DatagramPacket dpFromServer = new DatagramPacket(byteBuf, byteBuf.length);
        datagramSocket.receive(dpFromServer);
        byte[] bytesFromServer = dpFromServer.getData();
        Response responseFromServer = Serializer.deserializeResponse(bytesFromServer);
        return responseFromServer;
    }

}
