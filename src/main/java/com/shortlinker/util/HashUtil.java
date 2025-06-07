package com.shortlinker.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String getMD5Hash(String input) {
        try {
            //create instance of MD5 generator
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            //generate string using md instance of md5
            byte[] generatedString = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            //convert bytes to hex
            for (byte b : generatedString) {
                hexString.append(String.format("%02x", b));
            }

            //cut string to 7 characters
            return hexString.substring(0, 7);
        }
    catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
