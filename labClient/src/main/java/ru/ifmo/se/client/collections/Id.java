package ru.ifmo.se.client.collections;

/**
 * id field of person
 * @author raistlin
 */
public class Id extends AbstractField<Person, Integer> {
    
    @Override
    public void set(Person p, Integer id){
        p.setId(id);
    }
    
    @Override
    public boolean validate(String input){
        return false;
    }
    
    @Override
    public String ask(){
        return "";
    }
    
    @Override
    public Integer create(String Uselessinput){
        return 1;
    }
    
    @Override
    public String toString(){
        return "ID";
    }
}
