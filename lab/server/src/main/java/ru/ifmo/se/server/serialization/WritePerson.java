package ru.ifmo.se.server.serialization;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.collections.Person;
import ru.ifmo.se.server.parser.ObjectToCsv;

/**
 * Write collection to CSV format and save to file.(serialization)
 * @author raistlin
 */
public class WritePerson {
    
    /**
     * Serialize and write
     * @param list collection of Persons
     * @param filename Name of a file,where we'll save our CSV data
     * @return true, if no exceptions encountered
     * @throws IOException if error in file reading
     */
    public static Boolean write(ArrayList<Person> list,File filename) throws IOException{
        
        PrintWriter file = new PrintWriter(filename);
        
        try{
            PersonMappingStrategy mappingStrategy = new PersonMappingStrategy();
            ObjectToCsv csvParser = new ObjectToCsv(Person.class,",",mappingStrategy);
            String csvData = csvParser.convert((List<Object>)(List<?>)list);
            file.write(csvData);
            file.close();
            return true;
        
        } catch(Exception e){
            OutputManager.print(e.getMessage());
            file.close();
            return false;
        } 
    }
}
