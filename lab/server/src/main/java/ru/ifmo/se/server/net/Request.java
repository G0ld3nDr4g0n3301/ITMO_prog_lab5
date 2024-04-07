package ru.ifmo.se.server.net;

import java.io.Serializable;
import ru.ifmo.se.server.collections.Location;
import ru.ifmo.se.server.collections.Person;

public class Request implements Serializable{
    
    private static final long serialVersionUID = 4234661872345L;

    private Commands commandType;

    /**
     * status code 200 means Successfully performed an operation. 300 means "execute the command". 400 means "Your request has been performed, and please, execute this command". 404 means error in execution.
     */
    private Integer statusCode;
    private Person person = null;
    private String msg = null;
    private Integer id = null;
    private Location loc = null;

    {
        this.statusCode = 300;
    }
    
    public Person getPerson(){
        return this.person;
    }

    public Integer getId(){
        return this.id;
    }
    
    public Location getLoc(){
        return this.loc;
    }
    
    public void setLoc(Location loc){
        this.loc = loc;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }
    
    public Request(Commands commandType){
        this.commandType = commandType;
    }
    
    public Request(Commands commandType, Integer code) {
        this.commandType = commandType;
        this.statusCode = code;
    }

    public Request(Integer code){
        this.commandType = Commands.RESPONSE;
        this.statusCode = code;
    }

    public Request(Integer code, String msg){
        this.commandType = Commands.RESPONSE;
        this.statusCode = code;
        this.msg = msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public Integer getStatusCode(){
        return this.statusCode;
    }

    public String getMsg(){
        return this.msg;
    }

    public Commands getCommandType(){
        return this.commandType;
    }

    public void setStatusCode(Integer code){
        this.statusCode = code;
    }



}
