package ru.ifmo.se.lab.server.parser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateAnnotatedFieldsMap {
    private HashMap<String, HashMap<String, ArrayList>> map = new HashMap<>();
    
    HashMap collect(Class startClass){
        HashMap<String, ArrayList> submap = new HashMap<>();
        map.put(startClass.getName(), submap);
        
        for (Field f : startClass.getDeclaredFields()){
        
            if (f.isAnnotationPresent(CsvBindByName.class)){
                submap.put(f.getName(), new ArrayList<Object>());
            }
        
        }
        
        for (Field f : startClass.getDeclaredFields()){
            
            if (f.isAnnotationPresent(CsvRecurseBind.class)){
                this.collect(f.getType());
            }
        }
        
        return this.map;
    }
}
