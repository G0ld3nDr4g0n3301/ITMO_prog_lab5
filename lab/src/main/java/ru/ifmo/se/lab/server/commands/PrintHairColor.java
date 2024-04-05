package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.collections.Color;
import ru.ifmo.se.lab.server.net.Request;
import ru.ifmo.se.lab.server.Command;

/**
 * Prints all hair colors of collection's elements, in descending order
 * @author raistlin
 */
public class PrintHairColor extends Command{
    
    public PrintHairColor(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "PrintHairColor";
    }
    
    @Override
    public Request<ArrayList<Color>> execute(Serializable args){
        ArrayList<Color> hairColors = (ArrayList<Color>) CollectionManager.getHairColors();
        hairColors.sort(Comparator.reverseOrder());
        Request<ArrayList<Color>> request = new Request<>(400, hairColors);
        return request;
    }
}
