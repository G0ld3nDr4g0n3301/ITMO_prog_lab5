package ru.ifmo.se.client;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class-wrapper for FileReader.Made for easy input validation.
 * @author raistlin
 */
public class ReadFile{
    
    private FileReader reader;
    private Scanner scan;
    
    /**
     * Creates FileReader with given filename, and wraps it into Scanner.
     * @param filename
     * @throws IOException
     */
    public ReadFile(String filename) throws IOException{
        this.reader = new FileReader(filename);
        this.scan = new Scanner(this.reader);
    }
    
    /**
     *  Returns next line, if it exists.
     * @return one line from the file
     */
    public String readLine(){
        if(this.scan.hasNextLine()){
            return scan.nextLine();
        }
        return null;
    }
    
    /**
     * Closes reader and scanner.
     */
    public void close(){
        this.scan.close();
        try{
            this.reader.close();
        }catch(IOException e){
            System.out.println("Permission denied");
        }
    }
    
}
