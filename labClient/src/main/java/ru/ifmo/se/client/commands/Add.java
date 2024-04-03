package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.CollectionManager;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.client.collections.Person;

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
        CLIOutputManager.print("Successfully saved person into collection!");
        return true;
    }
}
