package ru.ifmo.se.server.parser;

import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ru.ifmo.se.server.collections.*;

/**
 * Used to convert Java Object into CSV String
 * @author raistlin
 */
public class ObjectToCsv {
    
    /**
     * Class, which fields will be serialized String.
     */
    private Class targetClass;
    
    /**
     * CSV separator. Can be any string.
     */
    private String separator = ",";
    
    /**
     * How to map the fields?
     */
    private MappingStrategy strategy;
    
    public ObjectToCsv(Class clas, String sep, MappingStrategy strat) throws CsvNotEnoughArgsException{
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
     * Transforms List of Objects into CSV String.
     * @param list list of objects
     * @return CSV of object list.
     * @throws CsvWrongStructureException 
     */
    public String convert(List<Object> list) throws CsvWrongStructureException{
        String result = "";
        for (Object obj : list){
            result += this.convert(obj) + "\n";
        }
        return result;
    }
    
    /**
     * Overloaded version of convert(List). Converts single object into CSV line.
     * @param obj Source object
     * @return CSV line of Object
     * @throws CsvWrongStructureException 
     */
    private String convert(Object obj) throws CsvWrongStructureException{
        String result = "\"";
        List<String> data = this.strategy.getObject(obj);
        for (String fieldValue : data){
            result += fieldValue + "\"" + separator + "\"";
        }
        
        return result.substring(0,result.length() - 2);
    }
    
    /**
     * getter for target Class
     * @return targetClass
     */
    public Class getTargetClass(){
        return this.targetClass;
    }
    
    /**
     * getter for strategy
     * @return strategy
     */
    public MappingStrategy getMappingStrategy(){
        return this.strategy;
    }
    
    /**
     * getter for separator.
     * @return separator
     */
    public String getSeparator(){
        return this.separator;
    }
    
}
