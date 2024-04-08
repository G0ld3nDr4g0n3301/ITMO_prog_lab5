package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.Command;

import java.io.Serializable;
import java.util.Arrays;

import ru.ifmo.se.client.Validator;

import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

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
    public Request execute(String[] args){
        if(args.length < 2){
            System.out.println("Not enough arguments");
            return null;
        }
        if (!Validator.validateInt(args[1])) {
            System.out.println("ID must be a number.");
            return null;
        }
        Request req = new Request(Commands.COUNT);
        req.setId(Integer.valueOf(args[1]));
        return req;
    }
}
