package ru.ifmo.se.lab.server.serialization;

import com.opencsv.bean.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import ru.ifmo.se.lab.server.collections.Person;

public class WritePerson {
    
    
    public static Boolean write(ArrayList<Person> list,File filename) throws IOException{
        
        FileWriter file = new FileWriter(filename);
        
        try{
        System.out.println("Пытается");
            String[] header = {"id","name","Coordinate X","Coordinate Y","Creation Date","Height","Birthday","Weight","Hair Color","Location X","Location Y","Location Name"};
            PersonMappingStrategy mappingStrategy = new PersonMappingStrategy();
            
        System.out.println("Не могёт.");
            StatefulBeanToCsvBuilder<Person> builder = new StatefulBeanToCsvBuilder(file).withMappingStrategy(mappingStrategy);
            StatefulBeanToCsv writer = builder.build();
            writer.write(list);
            file.close();
            return true;
        
        } catch(Exception e){
        
            file.close();
            return false;
        }
    }
}
