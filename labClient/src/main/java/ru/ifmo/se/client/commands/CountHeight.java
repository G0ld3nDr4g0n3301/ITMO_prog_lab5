package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.Command;

import java.io.Serializable;

import ru.ifmo.se.client.net.Commands;
import ru.ifmo.se.client.net.Request;

/**
 * Counts all persons with given height
 * @author raistlin
 */
public class CountHeight extends Command{
    
    public CountHeight(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "CountHeight";
    }
    
    @Override
    public Serializable execute(String[] args){
        if(args.length < 2){
            System.out.println("Not enough arguments");
            return null;
        }
        return new Request<>(Commands.COUNT);
    }
}
