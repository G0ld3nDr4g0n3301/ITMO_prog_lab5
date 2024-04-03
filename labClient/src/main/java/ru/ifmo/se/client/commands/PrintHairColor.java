package ru.ifmo.se.client.commands;

import java.util.Comparator;
import java.util.List;
import ru.ifmo.se.client.CollectionManager;
import ru.ifmo.se.client.collections.Color;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.collections.Color;
import ru.ifmo.se.client.collections.Color;

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
    public boolean execute(String[] args){
        List<Color> hairColors = CollectionManager.getHairColors();
        hairColors.sort(Comparator.reverseOrder());
        for(Color c : hairColors){
            CLIOutputManager.print(c);
        }
        return true;
    }
}
