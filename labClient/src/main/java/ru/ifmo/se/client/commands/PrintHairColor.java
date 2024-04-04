package ru.ifmo.se.client.commands;

import java.io.Serializable;
import ru.ifmo.se.client.net.Commands;
import ru.ifmo.se.client.net.Request;
import ru.ifmo.se.client.Command;

/**
 * Prints all hair colors of collection's elements, in descending order
 * @author raistlin
 */
public class PrintHairColor extends Command{
    
    public PrintHairColor(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "PrintHairColor";
    }
    
    @Override
    public Serializable execute(String[] args){
        return new Request<>(Commands.HAIR);
    }
}
