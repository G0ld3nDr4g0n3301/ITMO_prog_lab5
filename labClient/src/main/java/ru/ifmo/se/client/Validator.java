package ru.ifmo.se.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import ru.ifmo.se.client.collections.*;


/**
 * A bunch of different checks for given fields.
 * 
 * @author raistlin
 */

public class Validator {
    
    /**
     * Checks,if potential command is not "",and consists not only from spaces.
     * 
     * @param input Potential command
     * @return Result of validation.
     */
    public static boolean validateCommand(String input){
        if(input.length() == 0 || input.split(" ").length == 0){
            return false;
        } else{
            return true;
        }
    }
    
    
    public static boolean checkEmpty(String input){
        if(input == ""){
            return true;
        }
        return false;
    }
    

    
    /**
     * Checks if obj == null
     * @param obj
     * @return true if obj is null
     */
    public static boolean checkNull(Object obj){
        return obj == null;
    }
    
    public static boolean checkNull(Object[] obj){
        boolean res = false;
        for (Object i : obj){
            res = res || checkNull(i);
        }
        return res;
    }
    
    /**
     * Checks that all fields(except birthday) is not null.
     * 
     * @param p Person to validate
     * @return result of validation
     */
    public static boolean validatePerson(Person p){
        boolean good = p.getCoordinates() != null;
        good = good && p.getHairColor() != null;
        good = good && p.getHeight() != null;
        good = good && p.getLocation() != null;
        good = good && p.getName() != null;
        good = good && p.getWeight() != null;
        if(!good){
            CLIOutputManager.print("Wrong person credentials.");
        }
        return good;
    }
    
    
    /**
     * Checks if height is greater than zero.
     * 
     * @param input Height
     * @return result of validation
     */
    public static boolean validateHeight(String input){
        try{
            if(Long.parseLong(input) <= 0){
                throw new Exception();
            }
        } catch (Exception e){
            CLIOutputManager.print("Wrong options for height(must be >0)");
            return false;
        }
        return true;
    }
    
    
    /**
     * Check if weight > 0
     * @param input Weight
     * @return result of validation
     */
    public static boolean validateWeight(String input){
        try{
            if(Integer.parseInt(input) <= 0){
                throw new Exception();
            }
        } catch (Exception e){
            CLIOutputManager.print("Wrong options for weight(must be >0)");
            return false;
        }
        return true;
    }
    
    
    /**
     * Checks if date is correct
     * 
     * @param input Date.
     * @return result of validation
     */
    public static boolean validateBirthday(String input){
        if(input == null){
            return true;
        }
        try{
            LocalDate.parse(input, DateTimeFormatter.ISO_DATE);
        } catch( Exception e){
            CLIOutputManager.print("Wrong date format. Correct one is yyyy-mm-dd");
            return false;
        }
        return true;
    }
    
    /**
     * Checks if field Coordinates is not null.
     * @param c coordinates
     * @return result of validation
     */
    public static boolean validateCoords(Coordinates c){
        return c != null;
    }
    
    
    /**
     * Checks x > -92 and can be parsed to Double
     * 
     * @param x
     * @return result of validation
     */
    public static boolean validateCoordX(String x){
        try{
            Double newX = Double.parseDouble(x);
            if(newX > -92){
                return true;
            } else{
                throw new Exception();
            }
        } catch( Exception e){
            CLIOutputManager.print("Incorrect input for x(must be greater than -92).");
            return false;
        }
    }
    
    
    /**
     * Checks if y can be parsed to Long
     * 
     * @param y
     * @return result of validation
     */
    public static boolean validateCoordY(String y){
        try{
            Long newY = Long.parseLong(y);
        } catch (Exception e){
            CLIOutputManager.print("Wrong input in coordinate y");
            return false;
        }
        return true;
    }
    
    /**
     * Checks if Location is not null
     * 
     * @param loc
     * @return result of validation
     */
    public static boolean validateLoc(Location loc){
        return loc != null;
    }
    
    /**
     * Checks if x can be parsed to Float
     * 
     * @param x
     * @return result of validation
     */
    public static boolean validateLocX(String x){
        try{
            Float.parseFloat(x);
            return true;
        } catch(Exception e){
            CLIOutputManager.print("Wrong input for x.");
            return false;
        }
    }
    
    /**
     * Checks if y can be parsed to Double
     * 
     * @param y
     * @return result of validation
     */
    public static boolean validateLocY(String y){
        try{
            Double.parseDouble(y);
            return true;
        } catch(Exception e){
            CLIOutputManager.print("Wrong input for y.");
            return false;
        }
    }

    
    /**
     * Checks if such color exists in enum Color
     * 
     * @param input
     * @return result of validation
     */
    public static boolean validateColor(String input){
        try{
            Color.valueOf(input);
            return true;
        } catch (Exception e){
            CLIOutputManager.print("Wrong option for Color");
            return false;
        }
    }
    

}

