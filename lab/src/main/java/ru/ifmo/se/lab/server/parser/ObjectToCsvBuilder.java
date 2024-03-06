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
    
    public void withClass(Class cl){
        this.targetClass = cl;
    }
    
    public void withMappingStrategy(MappingStrategy strategy){
        this.strategy = strategy;
    }
    
    public void withSeparator(String separator){
        this.separator = separator;
    }
    
    public ObjectToCsv build() throws CsvNotEnoughArgsException{
        if(targetClass == null){
            throw new CsvNotEnoughArgsException("Must specify target class!");
        } else if (strategy == null){
            throw new CsvNotEnoughArgsException("Must specify mapping strategy!");
        } else if (targetFile == null){
            throw new CsvNotEnoughArgsException("Must specify file writer!");
        } else if (separator == null) {
            throw new CsvNotEnoughArgsException("Must specify the separator!");
        }
        return new ObjectToCsv(this.targetClass,this.targetFile,this.separator,this.strategy);
    }
    
    
}
