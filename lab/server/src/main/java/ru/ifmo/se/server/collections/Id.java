package ru.ifmo.se.server.collections;


import java.util.ArrayList;
import java.util.List;

import ru.ifmo.se.server.InputManager;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.Validator;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.server.CollectionManager;

/**
 * id field of person
 * @author raistlin
 */
public class Id extends AbstractField<Person, Integer> {
    
    @Override
    public void set(Person p, Integer id){
        p.setId(id);
    }
    
    @Override
    public boolean validate(String input){
        return false;
    }
    
    @Override
    public String ask(){
        return "";
    }
    
    @Override
    public Integer create(String Uselessinput){
        List<Person> collection = CollectionManager.getCollection();
        if(collection.size() == 0){
            return 1;
        }
        return collection.get(collection.size() - 1).getId() + 1;
    }
    
    @Override
    public String toString(){
        return "CreationDate";
    }
}
