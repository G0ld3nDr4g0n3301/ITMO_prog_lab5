package ru.ifmo.se.lab.server.parser;

import java.util.ArrayList;
import java.util.List;
import ru.ifmo.se.lab.server.OutputManager;

public class CsvToObject {
    
    private Class targetClass;
    private String separator;
    private MappingStrategy strategy;
    
    public CsvToObject(Class clas, String sep, MappingStrategy strat) throws CsvNotEnoughArgsException{
        
        if(clas == null){
            throw new CsvNotEnoughArgsException("Must specify target class!");
        } else if (strat == null){
            throw new CsvNotEnoughArgsException("Must specify mapping strategy!");
        }  else if (sep == null) {
            throw new CsvNotEnoughArgsException("Must specify the separator!");
        }
        
        this.targetClass = clas;
        this.separator = sep;
        this.strategy = strat;
    }
    
    public List<Object> parse(String csvData){
        ArrayList<Object> result = new ArrayList<>();
        String[] lines = csvData.split("\n");
        int i = 1;
        for (String line : lines){
            try{
                result.add(this.strategy.fillObject(line.substring(1, line.length() - 1).split("\",\"")));
            } catch (CsvWrongDataException e){
                OutputManager.print("Can't parse data in line " + i);
            } finally {
                i += 1;
            }
        }
        return result;
    }
    
}
