package ru.ifmo.se.client.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

import ru.ifmo.se.common.net.Request;

public class Deserialize {

    public static Request deserializeRequest(byte[] bytes) {
        System.out.println("dskjfsdl");
        ByteArrayInputStream byte_in = new ByteArrayInputStream(bytes);
        try {
            System.out.println("about to stuck");
            try (ObjectInputStream obj_in = new ObjectInputStream(byte_in)) {
                System.out.println("i won't get here, lol");
                System.out.println(Arrays.toString(byte_in.readAllBytes()));
                Object obj = obj_in.readObject();
                return (Request) obj; 
            }
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }
    
}
