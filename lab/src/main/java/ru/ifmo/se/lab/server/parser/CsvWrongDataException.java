package ru.ifmo.se.lab.server.parser;

/**
 * Thrown when parser can't parse CSV data(it's wrong or damaged)
 * @author raistlin
 */
public class CsvWrongDataException extends RuntimeException{
    private String msg;
    
    public CsvWrongDataException(String msg){
        this.msg = msg;
    }
    
    @Override
    public String getMessage(){
        return this.msg;
    }
}
