package ru.ifmo.se.lab.server.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.Validator;


public class AskPerson {
    private static ArrayList<AbstractField> simpleFields = new ArrayList<>();
    private static ArrayList<AbstractField> canBeNullFields = new ArrayList<>();
    private static ArrayList<AbstractField> complexFields = new ArrayList<>(); 
    private static ArrayList<AbstractField> toGenerate = new ArrayList<>();
    
    static {
        
        Id id = new Id();
        Name name = new Name(); //Поле не может быть null, Строка не может быть пустой
        CoordinatesField coordinates = new CoordinatesField(); //Поле не может быть null
//        CreationDate creationDate = new CreationDate(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        Height height = new Height(); //Значение поля должно быть больше 0
        Birthday birthday = new Birthday(); //Поле может быть null
        Weight weight = new Weight(); //Значение поля должно быть больше 0
//        HairColor hairColor = new HairColor(); //Поле не может быть null
        LocationField location = new LocationField(); //Поле не может быть null

        simpleFields.add(name);
        simpleFields.add(height);
        simpleFields.add(weight);
 //       simpleFields.add(hairColor);
        
        canBeNullFields.add(birthday);
        
        complexFields.add(coordinates);
        complexFields.add(location);
        
//        toGenerate.add(id);
//        toGenerate.add(creationDate);
    }
    
    public static Person generatePerson(String[] args){
        Person person = new Person();
        
        // Necessary fields
        for (int i = 0; i < simpleFields.size(); i++){
            try{
                String input = args[i+1];
                AbstractField field = simpleFields.get(i);
                if(!field.validate(input)){
                    OutputManager.print("Wrong input format for field " + field);
                    return null;
                }
                field.set(person, field.create(input));
            } catch(NullPointerException e){
                OutputManager.print("Not enough arguments.");
                return null;
            } catch(Exception e){
                OutputManager.print("Wrong input");
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
                        OutputManager.print("Wrong input format for field " + field);
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
                Object value = field.create(new String());
                if(value != null){
                    field.set(person, value);
                    end = true;
                }
            } while(!end);
        }
       
        if(Validator.validatePerson(person)){
            return person;
        }
        OutputManager.print("Not enough args.");
        return null;
    }
    
    public static ArrayList<AbstractField> getFields(){
        return simpleFields;
    }
}
