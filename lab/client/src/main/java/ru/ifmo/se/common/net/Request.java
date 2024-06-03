package ru.ifmo.se.common.net;

import java.io.Serializable;
import java.util.ArrayList;

import ru.ifmo.se.common.collections.Location;
import ru.ifmo.se.common.collections.Person;

public class Request implements Serializable{
    
    /**
     * Serial version UID to lower the risk of collisions
     */
    private static final long serialVersionUID = 4234661872345L;

    /**
     * Type of Command to execute
     */
    private Commands commandType;

    /**
     * status code 200 means Successfully performed an operation. 300 means "execute the command". 400 means "Your request has been performed, and please, execute this command". 404 means error in execution.
     */
    private Integer statusCode;

    /**
     * attached person
     */
    private Person person = null;

    /**
     * Attached string
     */
    private String msg = null;

    /**
     * attached integer
     */
    private Integer id = null;

    /**
     * attached location
     */
    private Location loc = null;

    private String login;
    private String password;
    private Integer ownerId;
    private String cookie;
    private ArrayList<Person> collection;

    public ArrayList<Person> getCollection(){
        return this.collection;
    }

    public void setCollection(ArrayList<Person> collection){
        this.collection = collection;
    }

    public String getCookie(){
        return this.cookie;
    }

    public void setCookie(String cookie){
        this.cookie = cookie;
    }

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
