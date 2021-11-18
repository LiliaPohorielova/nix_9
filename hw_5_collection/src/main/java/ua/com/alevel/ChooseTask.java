package ua.com.alevel;

import ua.com.alevel.mathSets.ArrayMathSet;
import ua.com.alevel.mathSets.CapacityMathSet;
import ua.com.alevel.mathSets.DefaultMathSet;
import static ua.com.alevel.util.Navigation.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChooseTask {

    public static void run() {
        helper();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice;

        try {
            while ((choice = input.readLine()) != null) {
                switch (choice) {
                    case "1":
                        new DefaultMathSet().run(input);
                        break;
                    case "2":
                        new CapacityMathSet().run(input);
                        break;
                    case "3":
                        new ArrayMathSet().run(input);
                        break;
                    /*case "4":
                        new SymbolRevers().run(input);
                        break;
                    case "5":
                        new StringsRevers().run(input);
                        break;*/
                    case "0":
                        System.out.println("\n======================= EXIT ========================");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Incorrect value. Please, try again.");
                        System.out.print("\nChoose action you want: ");
                        break;
                }
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }

    }
}
