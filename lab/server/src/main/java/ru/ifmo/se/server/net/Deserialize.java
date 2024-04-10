package ru.ifmo.se.server.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import ru.ifmo.se.common.net.Request;

public class Deserialize {

    public static Request deserializeRequest(byte[] bytes) throws StreamCorruptedException {
        ByteArrayInputStream byte_in = new ByteArrayInputStream(bytes);
        try {
            try (ObjectInputStream obj_in = new ObjectInputStream(byte_in)) {
                Object obj = obj_in.readObject();
                return (Request) obj;
            }
        } catch (StreamCorruptedException e){
            throw e;
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e);
            System.out.println("heheheh");
            return null;
        }
    }
    
}
