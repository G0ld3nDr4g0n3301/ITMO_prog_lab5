package ru.ifmo.se.lab.server.net;

import java.io.Serializable;

public class Request<T extends Serializable> implements Serializable{
    
    private Commands commandType;

    /**
     * status code 200 means Successfully performed an operation. 300 means "execute the command". 400 means "Your request has been performed, and please, execute this command". 404 means error in execution.
     */
    private Integer statusCode;
    private T arg;

    {
        this.statusCode = 300;
    }

    public Request(Commands commandType) {
        this.commandType = commandType;
    }

    public void setArgument(T argument){
        this.arg = argument;
    }

    public Integer getStatusCode(){
        return this.statusCode;
    }

    public T getArgs(){
        return this.arg;
    }

    public Commands getCommandType(){
        return this.commandType;
    }

    public void setStatusCode(Integer code){
        this.statusCode = code;
    }



}
