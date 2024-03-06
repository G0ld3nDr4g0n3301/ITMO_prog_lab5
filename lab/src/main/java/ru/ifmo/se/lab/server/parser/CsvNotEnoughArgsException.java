package ru.ifmo.se.lab.server.parser;

class CsvNotEnoughArgsException extends RuntimeException {
    private String msg;
    
    public CsvNotEnoughArgsException(String msg){
        this.msg = msg;
    }
    
    @Override
    public String getMessage(){
        return this.msg;
    }
}
