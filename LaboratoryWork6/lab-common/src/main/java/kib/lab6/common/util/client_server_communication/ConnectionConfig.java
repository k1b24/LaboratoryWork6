package kib.lab6.common.util.client_server_communication;

public final class ConnectionConfig {

    private static final int BYTE_BUFFER_SIZE = 4096;
    private static final int SERVER_PORT = 1337;

    private ConnectionConfig() {

    }

    public static int getByteBufferSize() {
        return BYTE_BUFFER_SIZE;
    }

    public static int getServerPort() {
        return SERVER_PORT;
    }
}
