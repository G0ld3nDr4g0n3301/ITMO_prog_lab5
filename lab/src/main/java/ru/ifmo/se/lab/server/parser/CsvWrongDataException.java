package ru.ifmo.se.lab.server.parser;

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
