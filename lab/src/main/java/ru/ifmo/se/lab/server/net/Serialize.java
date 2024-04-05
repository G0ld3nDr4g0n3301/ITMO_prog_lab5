package ru.ifmo.se.lab.server.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serialize {
    
    public static byte[] serializeRequest(Request r) {
        ByteArrayOutputStream byte_out = new ByteArrayOutputStream();
        try {
            try (ObjectOutputStream obj_out = new ObjectOutputStream(byte_out)) {
                obj_out.writeObject(r);
                obj_out.flush();
                return byte_out.toByteArray();
            }
        } catch (IOException e) {
            return null;
        }
    }
}
