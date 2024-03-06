package ru.ifmo.se.lab.server.serialization;

import ru.ifmo.se.lab.server.collections.*;
import ru.ifmo.se.lab.server.parser.*;
import com.opencsv.exceptions.*;
import java.io.IOException;
import ru.ifmo.se.lab.server.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Mapping strategy for serializing and deserializing Person object.
 * @author raistlin
 */
public class PersonMappingStrategy implements MappingStrategy{
    
    private String[] header = {"id","name","coordinates.cordX","coordinates.cordY","creationDate","height","birthday","weight","hairColor","location.locX","location.locY","location.locName"};
    
    /**
     * sets necessary types,CSV header and column mapping.
     */
    public PersonMappingStrategy(){
        
    }
    
    /**
     * Strategy for creating new Person object, different validations and comparisons.
     * @param line line of a CSV file,which is one Person object
     * @return Person object
     * @throws CsvBeanIntrospectionException
     * @throws CsvRequiredFieldEmptyException
     * @throws CsvDataTypeMismatchException
     * @throws CsvConstraintViolationException
     * @throws CsvValidationException 
     */
    
    @Override
    public Object fillObject(String[] line) {
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
        LocalDate birthday = line[6] == "" ? null : LocalDate.parse(line[6], DateTimeFormatter.ISO_DATE);
        Integer weight = Integer.parseInt(line[7]);
        Color color = Color.valueOf(line[8]);
        Float locX = Float.parseFloat(line[9]);
        Double locY = Double.parseDouble(line[10]);
        Location location = null;
        
        if(line.length > 11){
            location = new Location(locX,locY,line[11]);
        } else{
            location = new Location(locX,locY,null);
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
