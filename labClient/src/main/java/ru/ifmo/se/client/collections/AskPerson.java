package ru.ifmo.se.client.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import ru.ifmo.se.client.Invoker;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.Validator;

/**
 * Class that can easily generate new person, based on user input
 * @author raistlin
 */
public class AskPerson {
    /**
     * Array of simple fields, that must be entered in CL.
     */
    private static ArrayList<AbstractField> simpleFields = new ArrayList<>();
    
    /**
     * Array of fields, that can be null.
     */
    private static ArrayList<AbstractField> canBeNullFields = new ArrayList<>();
    
    /**
     * Fields,that require another fields.
     */
    private static ArrayList<AbstractField> complexFields = new ArrayList<>();
    
    /**
     * fields that should be generated automatically.
     */
    private static ArrayList<AbstractField> toGenerate = new ArrayList<>();
    
    static {
        
        Id id = new Id();
        Name name = new Name(); //Поле не может быть null, Строка не может быть пустой
        CoordinatesField coordinates = new CoordinatesField(); //Поле не может быть null
        CreationDate creationDate = new CreationDate(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        Height height = new Height(); //Значение поля должно быть больше 0
        Birthday birthday = new Birthday(); //Поле может быть null
        Weight weight = new Weight(); //Значение поля должно быть больше 0
        HairColor hairColor = new HairColor(); //Поле не может быть null
        LocationField location = new LocationField(); //Поле не может быть null

        simpleFields.add(name);
        simpleFields.add(height);
        simpleFields.add(weight);
        
        canBeNullFields.add(birthday);
        
        complexFields.add(coordinates);
        complexFields.add(location);
        complexFields.add(hairColor);
        
        toGenerate.add(id);
        toGenerate.add(creationDate);
    }
    
    /**
     * Method generates a new Person, based on user input
     * @param args user input, splitted by " ".
     * @return new person
     */
    public static Person generatePerson(String[] args){
        Person person = new Person();
        
        // Necessary fields
        for (int i = 0; i < simpleFields.size(); i++){
            try{
                String input = args[i+1];
                AbstractField field = simpleFields.get(i);
                if(!field.validate(input)){
                    CLIOutputManager.print("Wrong input format for field " + field);
                    return null;
                }
                field.set(person, field.create(input));
            } catch(NullPointerException e){
                CLIOutputManager.print("Not enough arguments.");
                return null;
            } catch(Exception e){
                CLIOutputManager.print("Wrong input");
                return null;
            }
        }
        
        // Ask about unnecessary fields
        if(!canBeNullFields.isEmpty()){
            for (int i = 0; i < canBeNullFields.size(); i++){
                if(args.length - simpleFields.size() - i - 1 > 0){
                    String input = args[simpleFields.size() + i + 1];
                    AbstractField field = canBeNullFields.get(i);
                    if(!field.validate(input)){
                        CLIOutputManager.print("Wrong input format for field " + field);
                        return null;
                    }
                    field.set(person, field.create(input)); 
                }
            }
        }
        
        // Ask about complex fields
        for(AbstractField field : complexFields){
            boolean end = false;
            do{
                Object value = field.create("");
                if(value != null){
                    field.ask();
                    field.set(person, value);
                    end = true;
                }
                if(Invoker.getModeState() && value == null){
                    end = true;
                }
            } while(!end);
        }
        
        // Generate autogenerable fields
        for(AbstractField field : toGenerate){
            field.set(person, field.create(""));
        }
        
        if(Validator.validatePerson(person)){
            return person;
        }
        CLIOutputManager.print("Not enough args.");
        return null;
    }
}    
