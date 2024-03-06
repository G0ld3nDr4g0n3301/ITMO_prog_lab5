package ru.ifmo.se.lab.server.parser;

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
