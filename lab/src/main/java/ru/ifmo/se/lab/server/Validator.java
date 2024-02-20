package ru.ifmo.se.lab.server;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import ru.ifmo.se.lab.server.collections.Color;
import ru.ifmo.se.lab.server.collections.Coordinates;
import ru.ifmo.se.lab.server.collections.Location;
import ru.ifmo.se.lab.server.collections.Person;

public class Validator {
    
    public static boolean validateCommand(String input){
        if(input.length() == 0 || input.split(" ").length == 0){
            return false;
        } else{
            return true;
        }
    }
    
    public static boolean validateId(String input){
        Integer id = null;
        try{
            id = Integer.parseInt(input);
            System.out.println(id);
            if(id <= 0){
                return false;
            }
        } catch(Exception e){
            return false;
        }
        for(Person p : CollectionManager.getCollection()){
            if(p.getId() == id){
                return false;
            }
        }
        return true;
    }
    
    public static boolean validateArg(String input){
        try{
            input.split("=");
        } catch(Exception e){
            return false;
        }
        return input.split(input).length == 2;
    }
    
    public static boolean validatePerson(Person p){
        boolean good = p.getCoordinates() != null;
        good = good && p.getHairColor() != null;
        good = good && p.getHeight() != null;
        good = good && p.getLocation() != null;
        good = good && p.getName() != null;
        good = good && p.getWeight() != null;
        if(!good){
            OutputManager.print("Wrong person credentials.");
        }
        return good;
    }
    
    public static boolean validateHeight(String input){
        try{
            if(Long.parseLong(input) <= 0){
                throw new Exception();
            }
        } catch (Exception e){
            OutputManager.print("Wrong options for height(must be >0)");
            return false;
        }
        return true;
    }
    
    public static boolean validateWeight(String input){
        try{
            if(Integer.parseInt(input) <= 0){
                throw new Exception();
            }
        } catch (Exception e){
            OutputManager.print("Wrong options for weight(must be >0)");
            return false;
        }
        return true;
    }
    
    public static boolean validateBirthday(String input){
        try{
            LocalDate.parse(input, DateTimeFormatter.ISO_DATE);
        } catch( Exception e){
            OutputManager.print("Wrong date format. Correct one is yyyy-mm-dd");
            return false;
        }
        return true;
    }
    
    
    public static boolean validateCoords(Coordinates c){
        return c != null;
    }
    
    public static boolean validateCoordX(String x){
        try{
            Double newX = Double.parseDouble(x);
            if(newX > -92){
                return true;
            } else{
                throw new Exception();
            }
        } catch( Exception e){
            OutputManager.print("Incorrect input for x(must be greater than -92).");
            return false;
        }
    }
    
    public static boolean validateCoordY(String y){
        try{
            Long newY = Long.parseLong(y);
        } catch (Exception e){
            OutputManager.print("Wrong input in coordinate y");
            return false;
        }
        return true;
    }
    
    public static boolean validateLoc(Location loc){
        return loc != null;
    }
    
    public static boolean validateLocX(String x){
        try{
            Float.parseFloat(x);
            return true;
        } catch(Exception e){
            OutputManager.print("Wrong input for x.");
            return false;
        }
    }
    
    public static boolean validateLocY(String y){
        try{
            Double.parseDouble(y);
            return true;
        } catch(Exception e){
            OutputManager.print("Wrong input for y.");
            return false;
        }
    }

    public static boolean validateColor(String input){
        try{
            Color.valueOf(input);
            return true;
        } catch (Exception e){
            OutputManager.print("Wrong option for Color");
            return false;
        }
    }
    
    public static boolean validateUniqueId(List<Person> list){
        for(int i = 0; i<list.size(); i++){
            int currId = list.get(i).getId();
            for(int j = i+1; j < list.size();j++){
                if(list.get(j).getId() == currId){
                    return false;
                }
            }
        }
        return true;
    }
}

