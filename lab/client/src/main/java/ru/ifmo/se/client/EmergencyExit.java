package ru.ifmo.se.client;

import ru.ifmo.se.client.commands.Exit;

public class EmergencyExit {
    public static void execute(){
        new Exit(null, null).execute(null);
    }

}
