package ru.ifmo.se.client.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Sender {
    
    public static <T extends Serializable> void send(T request, ObjectOutputStream out) throws IOException{
        out.writeObject(request);
    }
    
}
