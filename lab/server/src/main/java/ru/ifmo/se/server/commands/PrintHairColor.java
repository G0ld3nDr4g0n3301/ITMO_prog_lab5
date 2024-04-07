package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.common.collections.Color;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.Command;

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
    public Request execute(Request args){
        ArrayList<Color> hairColors = (ArrayList<Color>) CollectionManager.getHairColors();
        hairColors.sort(Comparator.reverseOrder());
        String colors = "";
        for (Color c : hairColors) {
            colors += c.toString() + "\n";
        }
        return new Request(400, colors);
    }
}
