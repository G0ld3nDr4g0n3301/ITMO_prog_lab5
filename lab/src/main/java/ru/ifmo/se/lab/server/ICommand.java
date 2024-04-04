package ru.ifmo.se.lab.server;

import java.io.Serializable;

import ru.ifmo.se.lab.server.net.Request;

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
    public Request execute(Serializable args);
}
