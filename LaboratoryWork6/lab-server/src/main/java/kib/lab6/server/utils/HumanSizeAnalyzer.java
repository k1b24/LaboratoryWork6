package kib.lab6.server.utils;

import kib.lab6.common.entities.HumanBeing;
import kib.lab6.common.util.client_server_communication.ConnectionConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public final class HumanSizeAnalyzer {

    private HumanSizeAnalyzer() {

    }

    public static Integer getHumanSize(HumanBeing human) {
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream(ConnectionConfig.getByteBufferSize());
             ObjectOutputStream oos = new ObjectOutputStream(bytes)) {
            oos.writeObject(human);
            return bytes.size();
        } catch (IOException e) {
            return Integer.MAX_VALUE;
        }
    }

}
