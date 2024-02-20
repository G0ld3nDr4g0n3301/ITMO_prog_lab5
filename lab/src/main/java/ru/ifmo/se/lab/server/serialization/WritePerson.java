package ru.ifmo.se.lab.server.serialization;

import com.opencsv.bean.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import ru.ifmo.se.lab.server.collections.Person;

public class WritePerson {
    
    
    public static Boolean write(ArrayList<Person> list,File filename) throws IOException{
        
        PrintWriter file = new PrintWriter(filename);
        
        try{
            String[] header = {"id","name","Coordinate X","Coordinate Y","Creation Date","Height","Birthday","Weight","Hair Color","Location X","Location Y","Location Name"};
            PersonMappingStrategy mappingStrategy = new PersonMappingStrategy();
            
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
