package ru.ifmo.se.lab.server;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile{
    private FileReader reader;
    private Scanner scan;
    
    public ReadFile(String filename) throws FileNotFoundException{
        this.reader = new FileReader(filename);
        this.scan = new Scanner(this.reader);
    }
    
    public String readLine(){
        if(this.scan.hasNextLine()){
            return scan.nextLine();
        }
        return null;
    }
    
    public void close(){
        this.scan.close();
        try{
            this.reader.close();
        }catch(IOException e){
            OutputManager.print("Permission denied");
        }
    }
    
}
