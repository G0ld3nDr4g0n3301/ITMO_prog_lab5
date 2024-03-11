package ru.ifmo.se.lab.server.serialization;

import ru.ifmo.se.lab.server.collections.*;
import ru.ifmo.se.lab.server.parser.*;
import java.io.IOException;
import ru.ifmo.se.lab.server.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapping strategy for serializing and deserializing Person object.
 * @author raistlin
 */
public class PersonMappingStrategy implements MappingStrategy{
    
    private String[] header = {"id","name","coordinates.cordX","coordinates.cordY","creationDate","height","birthday","weight","hairColor","location.locX","location.locY","location.locName"};
    
    
    @Override
    public List<String> getObject(Object obj){
        ArrayList<String> fields = new ArrayList<>();
        Person pers = (Person) obj;
        
        fields.add(pers.getId().toString());
        fields.add(pers.getName());
        fields.add(pers.getCoordinates().getX().toString());
        fields.add(pers.getCoordinates().getY().toString());
        fields.add(pers.getCreationDate().toString());
        fields.add(pers.getHeight().toString());
        if(!Validator.checkNull(pers.getBirthday())){
            fields.add(pers.getBirthday().toString());
        } else {
            fields.add("");
        }
        fields.add(pers.getWeight().toString());
        fields.add(pers.getHairColor().toString());
        fields.add(pers.getLocation().getLocX().toString());
        fields.add(pers.getLocation().getLocY().toString());
        if(!Validator.checkNull(pers.getLocation().getName())){
            fields.add(pers.getLocation().getName());
        } else {
            fields.add("");
        }
        
        return fields;
    }
    
    
    /**
     * sets necessary types,CSV header and column mapping.
     */
    public PersonMappingStrategy(){
        
    }
    
   
    
    @Override
    public Object fillObject(String[] old_line) throws CsvWrongDataException{
        String[] line = old_line;
        
        for(int i = 0; i< line.length; i++){
            if(Validator.checkEmpty(line[i])){
                line[i] = null;
            }
        }
        
        boolean good = Validator.validateId(line[0]) && (line[1] != null) && Validator.validateCoordX(line[2]) && Validator.validateCoordY(line[3]);
        good = good && Validator.validateBirthday(line[4]) && Validator.validateHeight(line[5]) && (line[6] == "" || Validator.validateBirthday(line[6])) && Validator.validateWeight(line[7]);
        good = good && Validator.validateColor(line[8]) && Validator.validateLocX(line[9]) && Validator.validateLocY(line[10]);
        if(!good){
            throw new CsvWrongDataException("the file contains wrong data.");
        }
        
        Person p = new Person();
        Integer id = Integer.parseInt(line[0]);
        String name = line[1];
        Double coordX = Double.parseDouble(line[2]);
        Long coordY = Long.parseLong(line[3]);
        Coordinates coord = new Coordinates(coordX, coordY);
        LocalDate crDate = LocalDate.parse(line[4], DateTimeFormatter.ISO_DATE);
        Long height = Long.parseLong(line[5]);
        LocalDate birthday = line[6] == null ? null : LocalDate.parse(line[6], DateTimeFormatter.ISO_DATE);
        Integer weight = Integer.parseInt(line[7]);
        Color color = Color.valueOf(line[8]);
        Float locX = Float.parseFloat(line[9]);
        Double locY = Double.parseDouble(line[10]);
        Location location = null;
        
        if(line.length == 12){
            location = new Location(locX,locY,line[11]);
        } else {
            location = new Location(locX, locY, null);
        }
        p.setId(id);
        p.setBirthday(birthday);
        p.setCoordinates(coord);
        p.setCreationDate(crDate);
        p.setHairColor(color);
        p.setHeight(height);
        p.setWeight(weight);
        p.setLocation(location);
        p.setName(name);
        
        return p;
    }
    
    @Override
    public String[] getHeader(){
        return this.header;
    }
    
    
}
