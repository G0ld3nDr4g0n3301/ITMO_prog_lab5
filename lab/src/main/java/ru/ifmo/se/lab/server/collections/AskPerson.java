package ru.ifmo.se.lab.server.collections;

import java.util.HashMap;
import ru.ifmo.se.lab.server.OutputManager;

public class AskPerson {
    private static HashMap<String,AbstractField> fields = new HashMap<>();
    
    static {
        Id id = new Id();
        
        fields.put("id", id);
    }
    
    public static Person generatePerson(){
        Person person = new Person();
        for (AbstractField c : fields.values()){
            String input = c.ask();
            if(!c.validate(input)){
                OutputManager.print("Incorrect input for field " + c);
                return null;
            }
            c.set(person, c.create(input));
        }
        return person;
    }
}
