package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.AskPerson;
import ru.ifmo.se.lab.server.collections.Person;

public class Add extends Command{
    
    public Add(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public boolean execute(String[] args){
        Person person = AskPerson.generatePerson();
        if(person == null){
            return false;
        }
        OutputManager.print(person);
        return true;
    }
}
