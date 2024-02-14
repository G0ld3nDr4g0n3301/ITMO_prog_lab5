package ru.ifmo.se.lab.server;


public class Command implements ICommand {
    public String name;
    public String description;
    
    public String getName(){
        return this.name;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    @Override
    public void execute(String[] args){
        
    }
}
