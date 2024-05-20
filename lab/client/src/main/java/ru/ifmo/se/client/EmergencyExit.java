package ru.ifmo.se.client;

import ru.ifmo.se.client.commands.Exit;

public class EmergencyExit {
    /**
     * Emergency exit. Usually invokes after Ctrl+C
     */
    public static void execute(){
        new Exit(null, null).execute(null);
    }

}
