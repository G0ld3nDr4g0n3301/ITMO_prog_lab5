package ru.ifmo.se.lab.server.collections;

public abstract class AbstractField<T,S> {
    
    public abstract void set(T objToChange,S newValue);
    public abstract String ask();
    public abstract S create(String input);
    public abstract boolean validate(String input);
}
