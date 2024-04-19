package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.common.collections.Color;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.LogFile;

/**
 * Prints all hair colors of collection's elements, in descending order
 * @author raistlin
 */
public class PrintHairColor extends Command{

    private static final Logger logger = Logger.getLogger(PrintHairColor.class.getName());
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
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
        List<Color> hairColors = CollectionManager.getHairColors().stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
        String colors = "";
        for (Color c : hairColors) {
            colors += c.toString() + "\n";
        }
        logger.info("Got sorted color list");
        return new Request(400, colors);
    }
}
