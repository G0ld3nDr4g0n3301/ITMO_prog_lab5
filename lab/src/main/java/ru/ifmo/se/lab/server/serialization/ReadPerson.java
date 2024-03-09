package ru.ifmo.se.lab.server.serialization;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.Validator;
import ru.ifmo.se.lab.server.collections.Person;
import ru.ifmo.se.lab.server.parser.CsvToObject;

/**
 * read CSV data from file, and parse it to list of Persons
 * @author raistlin
 */
public class ReadPerson {

    /**
     * read data from CSV file and deserialize it.
     * @param filename File with CSV data
     * @return Collection of Persons
     * @throws IOException
     * @throws NullPointerException 
     */
    public static List<Person> read(File filename) throws IOException,NullPointerException{
        
        FileReader reader = new FileReader(filename);
        Scanner scan = new Scanner(reader);
        String csvData = "";
        while (scan.hasNextLine()){
            csvData += scan.nextLine() + "\n";
        }
        
        try{
            PersonMappingStrategy strategy = new PersonMappingStrategy();
            List<Person> list = (List<Person>)(List<?>) new CsvToObject(Person.class,",",strategy).parse(csvData);
            if(!Validator.validateUniqueId(list)){
                OutputManager.print("id is not unique!");
                return null;
            }
            return list;
        } catch(Exception e){
            return null;
        }
    }
    
}
