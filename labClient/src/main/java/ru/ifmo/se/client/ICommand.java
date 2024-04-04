package ru.ifmo.se.client;

import java.io.Serializable;

/**
 * Interface with a single method for executing commands.
 * @author raistlin
 */
@FunctionalInterface
public interface ICommand {
    /**
     * execute command.Depends on concrete implementation.
     * @param args
     * @return true, if no exceptions encountered
     */
    public Serializable execute(String[] args);
}
