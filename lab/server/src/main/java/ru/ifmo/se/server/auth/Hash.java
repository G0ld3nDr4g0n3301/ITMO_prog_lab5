package ru.ifmo.se.server.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ru.ifmo.se.server.LogFile;

public class Hash {
    public static String hash(String input){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            byte[] md = messageDigest.digest(input.getBytes());
            BigInteger intRepresentation = new BigInteger(1, md);
            String hash = intRepresentation.toString();
            while(hash.length() < 32){
                hash = "0" + hash;
            }
            return hash;
        } catch (NoSuchAlgorithmException e){
            LogFile.warning("No such algorithm for hashing.");
            return null;
        }
    }

    
}
