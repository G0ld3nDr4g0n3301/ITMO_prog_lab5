package ru.ifmo.se.lab.server;

/**
 * Abstract class for commands.
 * @author raistlin
 */
public abstract class Command implements ICommand {
    /**
     * name of command
     */
    protected String name;
    /**
     * it's description
     */
    protected String description;
    
    /**
     * returns the name of command
     * @return name of command
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * returns it's description
     * @return description of command
     */
    public String getDescription(){
        return this.description;
    }
    
}
