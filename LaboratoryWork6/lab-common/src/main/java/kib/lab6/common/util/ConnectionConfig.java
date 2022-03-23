package kib.lab6.common.util;

public class ConnectionConfig {

    private static final int BYTE_BUFFER_SIZE = 4096;
    private static final int SERVER_PORT = 1337;

    public static int getByteBufferSize() {
        return BYTE_BUFFER_SIZE;
    }

    public static int getServerPort() {
        return SERVER_PORT;
    }
}
