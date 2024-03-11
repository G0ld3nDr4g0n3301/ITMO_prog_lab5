package ru.ifmo.se.lab.server.parser;

/**
 * Thrown when CSV Structure is broken in the file.
 * @author raistlin
 */
public class CsvWrongStructureException extends RuntimeException{
    
    private String msg;
    
    public CsvWrongStructureException(String msg){
        this.msg = msg;
    }
    
    @Override
    public String getMessage(){
        return this.msg;
    }
}
