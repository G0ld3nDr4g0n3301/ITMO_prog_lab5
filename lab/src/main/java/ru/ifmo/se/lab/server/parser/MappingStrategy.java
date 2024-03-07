package ru.ifmo.se.lab.server.parser;

import java.util.List;


public interface MappingStrategy {
    
    public String[] getHeader();
    
    public Object fillObject(String[] lines);
    
    public List<String> getObject(Object obj);
}
