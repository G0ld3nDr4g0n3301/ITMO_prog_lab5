package ru.ifmo.se.lab.server.parser;


public interface MappingStrategy {
    
    public String[] getHeader();
    
    public Object fillObject(String[] lines);
}
