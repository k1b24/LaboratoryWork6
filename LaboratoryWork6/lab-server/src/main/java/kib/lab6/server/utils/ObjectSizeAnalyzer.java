package kib.lab6.server.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public final class ObjectSizeAnalyzer {

    private ObjectSizeAnalyzer() {

    }

    public static Integer getSize(Object o) {
        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bytes)) {
            oos.writeObject(o);
            return bytes.size();
        } catch (IOException e) {
            return Integer.MAX_VALUE;
        }
    }
}
