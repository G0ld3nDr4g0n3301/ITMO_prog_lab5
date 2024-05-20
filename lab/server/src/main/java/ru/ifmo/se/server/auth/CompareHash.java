package ru.ifmo.se.server.auth;

import ru.ifmo.se.server.LogFile;

public class CompareHash {
    public static Boolean compare(String pass, String salt, String[] peppers, String hash) {
        String salted = pass + salt;
        for (String pepper : peppers) {
            String hashedPass = Hash.hash(salted + pepper);
            if (hashedPass == null) {
                LogFile.warning("Hashing error");
            }
            if (hashedPass.equals(hash)) {
                System.out.println("PASSWORD IS CORRECT!");
                return true;
            }
        }
        return false;
    }
    
}
