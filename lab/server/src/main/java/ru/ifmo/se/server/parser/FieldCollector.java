package ru.ifmo.se.server.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ru.ifmo.se.server.collections.Person;

/**
 * Collects target class fields that are binded with CsvBind annotation.
 * @deprecated
 * @author raistlin
 */
public class FieldCollector {
    
    /**
     * Collects object's fields into a string.
     * @param obj Object to collect data from
     * @param parser Parser to use for parsing
     * @return List of object's fields.
     * @throws NoSuchFieldException
     * @throws CsvWrongStructureException
     * @throws IllegalAccessException 
     */
    static List<String> collect(Object obj, ObjectToCsv parser) throws NoSuchFieldException, CsvWrongStructureException, IllegalAccessException{
        
        ArrayList<String> fieldData = new ArrayList<>();
        for (String path : parser.getMappingStrategy().getHeader()){
            
            String[] splitPath = path.split(".");
            
            Field currField = parser.getTargetClass().getDeclaredField(path);
            
            if(splitPath.length != 0){
                currField = parser.getTargetClass().getDeclaredField(splitPath[0]);
            }
            if(!(currField.isAnnotationPresent(CsvBind.class))){
                throw new CsvWrongStructureException("No annotation present near field.");
            }
            
            for (int i = 1; i < splitPath.length; i++){
                if(currField.isAnnotationPresent(CsvBind.class)){
                    currField = currField.getType().getDeclaredField(splitPath[i]);
                } else {
                    throw new CsvWrongStructureException("No annotation present near field.");
                }
            }
            
            if(!currField.isAnnotationPresent(CsvBind.class)){
                throw new CsvWrongStructureException("No annotation present near field.");
            }
            
            currField.setAccessible(true);
            Field f = Person.class.getDeclaredField("id");
            f.setAccessible(true);
            Object object = (Object) f.get(obj);
            System.out.println(object);
            f.get(obj);
            System.out.println("YAY");
            fieldData.add(currField.get(obj).toString());
            System.out.println("jfhdskhf");
        }
        
        return fieldData;
    }
}
