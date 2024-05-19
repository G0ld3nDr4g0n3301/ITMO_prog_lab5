package ru.ifmo.se.common.net;

import java.io.Serializable;
import ru.ifmo.se.common.collections.Location;
import ru.ifmo.se.common.collections.Person;

public class Request implements Serializable{
    
    /**
     * serial id for serialization.
     */
    private static final long serialVersionUID = 4234661872345L;

    /**
     * Type of the command to be executed
     */
    private Commands commandType;

    /**
     * status code 200 means Successfully performed an operation. 300 means "execute the command". 400 means "Your request has been performed, and please, execute this command". 404 means error in execution.
     */
    private Integer statusCode;
    private Person person = null;
    private String msg = null;
    private Integer id = null;
    private Location loc = null;

    private String login;
    private String password;

    private Integer ownerId;

    public void setOwnerId(Integer id){
        this.ownerId = id;
    }
    public Integer getOwnerId(){
        return this.ownerId;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getLogin(){
        return this.login;
    }

    public String getPassword(){
        return this.password;
    }

    {
        // by default code is 300
        this.statusCode = 300;
    }
    
    /**
     * get person attached to request
     * @return attached person
     */
    public Person getPerson(){
        return this.person;
    }


    /**
     * get integer attached to request
     * @return attached integer
     */
    public Integer getId(){
        return this.id;
    }
    

    /**
     * get location attached to request
     * @return attached location
     */
    public Location getLoc(){
        return this.loc;
    }
    

    /**
     * Attach a location to request
     * @param loc location to attach
     */
    public void setLoc(Location loc){
        this.loc = loc;
    }
    


    /**
     * Attach a integer to request
     * @param id integer to attach
     */
    public void setId(Integer id) {
        this.id = id;
    }
    

    /**
     * Attach a person to request
     * @param person person to attach
     */
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

    /**
     * attaches a string to request
     * @param msg string to attach
     */
    public void setMsg(String msg){
        this.msg = msg;
    }

    /**
     * get request status code
     * @return status code
     */
    public Integer getStatusCode(){
        return this.statusCode;
    }

    /**
     * gets a string, attached to request
     * @return attached string
     */
    public String getMsg(){
        return this.msg;
    }


    /**
     * returns type of a command to execute
     * @return command type
     */
    public Commands getCommandType(){
        return this.commandType;
    }

    /**
     * set a status code to request
     * @param code status code
     */
    public void setStatusCode(Integer code){
        this.statusCode = code;
    }

    @Override
    public String toString(){
        String result = "";
        result += this.commandType.toString() + "\n";
        result += this.statusCode.toString() + "\n";
        if (this.person != null){
            result += this.person.toString() + "\n";
        }
        if (this.msg != null) {
            result += this.msg + "\n";
        }
        if(this.loc != null) {
        result += this.loc + "\n";
        }
        if (this.id != null) {
        result += this.id.toString() + "\n";
        }
        return result;
    }

}
