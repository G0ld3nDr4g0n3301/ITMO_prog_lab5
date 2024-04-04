package ru.ifmo.se.client.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.client.collections.Person;
import ru.ifmo.se.client.net.Commands;
import ru.ifmo.se.client.net.Request;

/**
 * replace person with given id,with another person 
 * @author raistlin
 */
public class Update extends Command{
    
    
    public Update(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Update";
    }
    
    @Override
    public Serializable execute(String[] args){
        if(args.length < 5){
            System.out.println("Not enough arguments.");
            return null;
        }
        Integer id = null;
        try{
            id = Integer.parseInt(args[1]);
        } catch(NullPointerException e){
            System.out.println("Not enough arguments.");
            return null;
        } catch (NumberFormatException e){
            System.out.println("id must be a number");
            return null;
        }
        args = Arrays.copyOfRange(args, 1, args.length - 1);
        Person newPerson = AskPerson.generatePerson(args);
        if(newPerson == null){
            System.out.println("Error in creating new Person. Try again.");
            return null;
        }
        
        Request<ArrayList<Serializable>> request = new Request<>(Commands.UPDATE);
        ArrayList<Serializable> arguments = new ArrayList<>();
        arguments.add(id, newPerson);
        request.setArgument(arguments);
        return request;
    }
}
