package ru.ifmo.se.lab.server.parser;

import java.util.ArrayList;
import java.util.List;

public class CsvToObject {
    
    private Class targetClass;
    private String separator;
    private MappingStrategy strategy;
    
    CsvToObject(Class clas, String sep, MappingStrategy strat) throws CsvNotEnoughArgsException{
        
        if(targetClass == null){
            throw new CsvNotEnoughArgsException("Must specify target class!");
        } else if (strategy == null){
            throw new CsvNotEnoughArgsException("Must specify mapping strategy!");
        }  else if (separator == null) {
            throw new CsvNotEnoughArgsException("Must specify the separator!");
        }
        
        this.targetClass = clas;
        this.separator = sep;
        this.strategy = strat;
    }
    
    public List<Object> parse(String csvData){
        ArrayList<Object> result = new ArrayList<>();
        String[] lines = csvData.split("\n");
        for (String line : lines){
            result.add(this.strategy.fillObject(line.substring(1, line.length() - 1).split("\",\"")));
        }
        return result;
    }
    
}
