package ru.ifmo.se.lab.server.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserialize {

    public static Request deserializeRequest(byte[] bytes) {
        ByteArrayInputStream byte_in = new ByteArrayInputStream(bytes);
        try {
            System.out.println("FAAFAF");
            try (ObjectInputStream obj_in = new ObjectInputStream(byte_in)) {
                Object obj = obj_in.readObject();
                return (Request) obj;
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e);
            return null;
        }
    }
    
}
