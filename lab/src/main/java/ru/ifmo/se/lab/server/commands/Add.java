package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.AskPerson;
import ru.ifmo.se.lab.server.collections.Person;

/**
 * Command for adding new person
 * @author raistlin
 */
public class Add extends Command{
    
    public Add(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public boolean execute(String[] args){
        Person person = AskPerson.generatePerson(args);
        if(person == null){
            return false;
        }
        CollectionManager.add(person);
        OutputManager.print("Successfully saved person into collection!");
        return true;
    }
}
