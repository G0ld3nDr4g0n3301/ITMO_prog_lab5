package ru.ifmo.se.client.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

import ru.ifmo.se.common.net.Request;

public class Deserialize {

    public static Request deserializeRequest(byte[] bytes) {
        ByteArrayInputStream byte_in = new ByteArrayInputStream(bytes);
        int length = bytes.length - 54;
        Object obj = null;
        try {
            try (ObjectInputStream obj_in = new ObjectInputStream(byte_in)) {
                obj = obj_in.readObject();
                if (obj == null) {
                    System.out.println("ERROR");
                }
                return (Request) obj;
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e);
            return null;
        }
    }
    
}
