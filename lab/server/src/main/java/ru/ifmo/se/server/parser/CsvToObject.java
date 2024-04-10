package ru.ifmo.se.server.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts CSV String into List of objects.
 * @author raistlin
 */
public class CsvToObject {
    
    /**
     * Target Class
     */
    private Class targetClass;
    
    /**
     * CSV Separator(can be any value).
     */
    private String separator;
    
    /**
     * Strategy for mapping objects.
     */
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
    
    /**
     * Parses CSV lines String into list of Objects.
     * @param csvData CSV string, red from file.
     * @return List of deserialized Objects
     */
    public List<Object> parse(String csvData){
        ArrayList<Object> result = new ArrayList<>();
        String[] lines = csvData.split("\n");
        int i = 1;
        for (String line : lines){
            try{
                result.add(this.strategy.fillObject(line.substring(1, line.length() - 1).split("\"" + this.separator + "\"")));
            } catch (CsvWrongDataException e){
                System.out.println("Can't parse data in line " + i);
            } finally {
                i += 1;
            }
        }
        return result;
    }
    
}
