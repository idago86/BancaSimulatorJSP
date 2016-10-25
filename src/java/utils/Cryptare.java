/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Israel Dago
 */
public final class Cryptare {
    private Cryptare(){}
    
    public static String hidePassword(String parola){
        StringBuilder result = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.reset();
            byte[] input = parola.getBytes();
            byte[] digested = md.digest(input);
            for(int i=0; i<digested.length;i++){
                result.append(Integer.toHexString(255&digested[i]));
            }
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Cryptare password esuat");
        }
        return result.toString();
    }
}
