package ru.ifmo.se.lab.server.collections;

/**
 * Abstract class for all fields of person.
 * @author raistlin
 * @param <T> Class,that owns this field.
 * @param <S> Type of a field
 */
public abstract class AbstractField<T,S> {
    
    /**
     * Set new value to the field of parent class.
     * @param objToChange
     * @param newValue 
     */
    public abstract void set(T objToChange,S newValue);
    
    /**
     * Get string value of field from user input
     * @return field value(string)
     */
    public abstract String ask();
    
    /**
     * creates value of field from it's string equivalent.
     * @param input
     * @return 
     */
    public abstract S create(String input);
    
    /**
     * Validates, if field value is correct
     * @param input string value
     * @return true, if OK.
     */
    public abstract boolean validate(String input);
}
