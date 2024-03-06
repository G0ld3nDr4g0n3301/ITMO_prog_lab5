package ru.ifmo.se.lab.server.parser;

import java.io.Writer;

public class ObjectToCsvBuilder {
    private MappingStrategy strategy;
    private Class targetClass;
    private Writer targetFile;
    private String separator = ",";
    
    
    public ObjectToCsvBuilder(Writer writer){
        this.targetFile = writer;
    }
    
    
}
