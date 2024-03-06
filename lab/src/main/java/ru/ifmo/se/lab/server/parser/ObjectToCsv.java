package ru.ifmo.se.lab.server.parser;

import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ObjectToCsv {
    private HashMap<String, HashMap<String,ArrayList>> classFieldMap;
    private Class targetClass;
    private String separator;
    private MappingStrategy strategy;
    private Writer targetFile;
    
    ObjectToCsv(Class clas, Writer file, String sep, MappingStrategy strat){
        this.targetClass = clas;
        this.targetFile = file;
        this.separator = sep;
        this.strategy = strat;
        this.init();
    }
    
    private void init(){
        this.classFieldMap = new CreateAnnotatedFieldsMap().collect(this.targetClass);
    }
    
    
    
    
}
