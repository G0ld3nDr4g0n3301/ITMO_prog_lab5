package ru.ifmo.se.lab.server.serialization;

import com.opencsv.bean.*;
import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.bean.exceptionhandler.ExceptionHandlerIgnore;
import com.opencsv.exceptions.CsvException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.Validator;
import ru.ifmo.se.lab.server.collections.Person;

public class ReadPerson {

    public static List<Person> read(File filename) throws FileNotFoundException,NullPointerException{
        
        FileReader reader = new FileReader(filename);
        
        try{
            PersonMappingStrategy strategy = new PersonMappingStrategy();
            CsvExceptionHandler exceptionHandler = new ExceptionHandlerIgnore();
            
            CsvToBeanBuilder<Person> builder = new CsvToBeanBuilder<Person>(reader).withMappingStrategy(strategy);
            builder.withExceptionHandler(exceptionHandler);
            CsvToBean<Person> csv = builder.build();
            List<Person> list = csv.parse();
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
