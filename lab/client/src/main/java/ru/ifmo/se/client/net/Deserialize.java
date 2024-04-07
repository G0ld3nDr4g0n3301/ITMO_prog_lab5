package ru.ifmo.se.client.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import ru.ifmo.se.common.net.Request;

public class Deserialize {

    public static Request deserializeRequest(byte[] bytes) {
        ByteArrayInputStream byte_in = new ByteArrayInputStream(bytes);
        try {
            try (ObjectInputStream obj_in = new ObjectInputStream(byte_in)) {
                Object obj = obj_in.readObject();
                return (Request) obj; 
            }
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }
    
}
