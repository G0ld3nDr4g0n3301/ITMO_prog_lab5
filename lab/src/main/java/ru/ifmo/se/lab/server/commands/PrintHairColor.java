package ru.ifmo.se.lab.server.commands;

import java.util.Comparator;
import java.util.List;
import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.collections.Color;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.Color;
import ru.ifmo.se.lab.server.collections.Color;

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
            OutputManager.print(c);
        }
        return true;
    }
}
