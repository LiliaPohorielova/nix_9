package ua.com.alevel;

import ua.com.alevel.dateAdd.AddNewDate;
import ua.com.alevel.datePrint.PrintListOfDates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.com.alevel.util.Navigation.helper;

public class ChooseTask {

    public static void run() {
        helper();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice;

        try {
            while ((choice = input.readLine()) != null) {
                switch (choice) {
                    case "1" -> new AddNewDate().run();
                    case "2" -> new PrintListOfDates().run();
                    /*case "2" -> new DateSort().run(input);
                    /*case "3" -> new ArrayMathSet().run(input);
                    case "4" -> new ArraysMathSet().run(input);
                    case "5" -> new MathSetWithMathSet().run(input);
                    case "6" -> new MathSetWithMathSets().run(input);*/
                    case "0" -> {
                        System.out.println("\n======================= EXIT ========================");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Incorrect value. Please, try again.");
                        System.out.print("\nChoose action you want: ");
                    }
                }
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}
