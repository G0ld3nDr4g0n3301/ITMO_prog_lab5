package ru.ifmo.se.lab.server.collections;


import java.util.ArrayList;
import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.Validator;
import ru.ifmo.se.lab.server.CollectionManager;

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
        ArrayList<Person> collection = CollectionManager.getCollection();
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
