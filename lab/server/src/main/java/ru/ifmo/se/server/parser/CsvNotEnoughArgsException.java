package ru.ifmo.se.server.parser;

/**
 * Thrown when CSV file has not enough columns
 * @author raistlin
 */
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
