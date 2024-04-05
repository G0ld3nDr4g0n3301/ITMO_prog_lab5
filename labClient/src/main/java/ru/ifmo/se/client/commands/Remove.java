package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.Command;

import java.io.Serializable;

import ru.ifmo.se.client.net.Commands;
import ru.ifmo.se.client.net.Request;

/**
 * remove given person from the collection
 * @author raistlin
 */
public class Remove extends Command{
    
    public Remove(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Remove";
    }
    
    @Override
    public Request execute(String[] args){
        Integer id = null;
        try{
            id = Integer.parseInt(args[1]);
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Not enough arguments.");
            return null;
        }catch (NumberFormatException e){
            System.out.println("id must be a number");
            return null;
        }
        Request request = new Request(Commands.REMOVE_BY_ID);
        request.setId(id);
        return request;
    }
}
