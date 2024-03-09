package ru.ifmo.se.lab.server.parser;

import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ru.ifmo.se.lab.server.collections.*;

public class ObjectToCsv {
    
    private Class targetClass;
    private String separator = ",";
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
    
    public String convert(List<Object> list) throws CsvWrongStructureException{
        String result = "";
        for (Object obj : list){
            result += this.convert(obj) + "\n";
        }
        return result;
    }
    
    private String convert(Object obj) throws CsvWrongStructureException{
        String result = "\"";
        List<String> data = this.strategy.getObject(obj);
        for (String fieldValue : data){
            result += fieldValue + "\"" + separator + "\"";
        }
        
        return result.substring(0,result.length() - 2);
    }
    
    public Class getTargetClass(){
        return this.targetClass;
    }
    
    public MappingStrategy getMappingStrategy(){
        return this.strategy;
    }
    
    public String getSeparator(){
        return this.separator;
    }
    
}
