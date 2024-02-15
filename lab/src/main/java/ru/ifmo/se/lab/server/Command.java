package ru.ifmo.se.lab.server;


public abstract class Command implements ICommand {
    protected String name;
    protected String description;
    
    public String getName(){
        return this.name;
    }
    
    public String getDescription(){
        return this.description;
    }
    
}
