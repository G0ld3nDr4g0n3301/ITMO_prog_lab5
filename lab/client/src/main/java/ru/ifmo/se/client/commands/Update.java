package ru.ifmo.se.client.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.LogFile;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

/**
 * replace person with given id,with another person 
 * @author raistlin
 */
public class Update extends Command{
    
    private static Person p = null;

    private static final Logger logger = Logger.getLogger(Add.class.getName());

    static {
            logger.addHandler(LogFile.getHandler());
    }
    
    public Update(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Update";
    }
    
    public static void setPerson(Person newP){
        p = newP;
    }

    @Override
    public Request execute(String[] args){
        System.out.println(p);
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
        if(p == null){
            System.out.println("Error in creating new Person. Try again.");
            return null;
        }
        
        Request request = new Request(Commands.UPDATE);
        request.setId(id);
        request.setPerson(p);
        logger.info("Sending UPDATE request");
        return request;
    }
}
