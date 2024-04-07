package ru.ifmo.se.server.parser;

import java.util.List;

/**
 * Interface for all strategies 
 * @author raistlin
 */
public interface MappingStrategy {
    
    /**
     * Get Strategy Header
     * @return array of strings(which usually the object's fields' names).
     */
    public String[] getHeader();
    
    /**
     * Fills an object with CSV data
     * @param lines CSV line, separated.
     * @return Deserialized object.
     */
    public Object fillObject(String[] lines);
    
    /**
     * Gets a string representation of Object's fields
     * @param obj Object to collect data from
     * @return List of Strings(object's fields).
     */
    public List<String> getObject(Object obj);
}
