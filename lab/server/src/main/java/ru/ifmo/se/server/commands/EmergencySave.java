package ru.ifmo.se.server.commands;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.net.ConnectionManager;

public class EmergencySave {

    private static final Logger logger = Logger.getLogger(EmergencySave.class.getName());

    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public static void save(){
        try {
        /*Set<SelectionKey> keys = ConnectionManager.getKeys();
        Iterator<SelectionKey> iter = keys.iterator();
        while (iter.hasNext()) {
            SelectionKey key = iter.next();
            key.cancel();
            iter.remove();
        } */
        ConnectionManager.close();
        System.exit(0);
        } catch (IOException e){
            System.exit(0);
        }
    }
    
}
