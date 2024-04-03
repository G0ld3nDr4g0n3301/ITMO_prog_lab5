package ru.ifmo.se.client;

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
    public boolean execute(String[] args);
}
