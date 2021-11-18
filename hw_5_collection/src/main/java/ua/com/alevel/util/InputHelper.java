package ua.com.alevel.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class InputHelper {

    public static Number enterNumber(BufferedReader reader) {
        String value = null;
        Number number = 0;
        try {
            value = reader.readLine();
            number = NumberUtils.createNumber(value);
            return number;
        } catch (NumberFormatException | IOException e) {
            System.out.println("Error: "+ e.getMessage());
        }
        System.out.println("Your number is: "+ number);
        System.out.println("Your class is: "+ number.getClass().getName());
        return null;
    }
}
