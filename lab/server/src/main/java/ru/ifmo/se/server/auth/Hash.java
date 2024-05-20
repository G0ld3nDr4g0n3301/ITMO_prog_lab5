package ru.ifmo.se.server.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import ru.ifmo.se.server.LogFile;

public class Hash {

    public static String hash(String pass, String salt, String[] peppers) {
        String salted = pass + salt;
        Random random = new Random();
        String pepper = peppers[random.nextInt(7)];
        String hashedPass = hash(salted + pepper);
        return hashedPass;
    }

    public static String hash(String input){

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            String hashValue = "";
            try {
                messageDigest.update(input.getBytes());
                byte[] digestedBytes = messageDigest.digest();
                String str = "";
                for (Byte bytes : digestedBytes){
                    str += String.format("%02X", bytes);
                }
                hashValue = str.toLowerCase();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return hashValue;
        } catch (NoSuchAlgorithmException e){
            LogFile.warning("No such algorithm for hashing.");
            return null;
        }
    }

    
}
