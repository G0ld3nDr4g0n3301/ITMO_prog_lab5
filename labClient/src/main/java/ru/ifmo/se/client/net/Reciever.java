package ru.ifmo.se.client.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Reciever {
    
    public static <T> T recieve(ObjectInputStream in) throws IOException, ClassCastException, ClassNotFoundException{
        Object obj = in.readObject();
        if (!(obj instanceof Serializable)){
            throw new ClassCastException("Error in server object sending.");
        }
        T request = (T) obj;
        return request;
    }
}
