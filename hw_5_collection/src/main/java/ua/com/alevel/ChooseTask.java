package ua.com.alevel;

import ua.com.alevel.tasks.CreateMathSet;

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
                        new CreateMathSet().run(input);
                        break;
                    /*case "2":
                        new SubstringRevers().run(input);
                        break;
                    case "3":
                        new IndexRevers().run(input);
                        break;
                    case "4":
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
                        System.out.print("\nTask number you want: ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void helper() {
        System.out.println("\n===================== MAIN MENU =======================");
        System.out.println("Action with the Math Set: ");
        System.out.println("1 - Create");
        System.out.println("2 - Add elements");
        /*System.out.println("3 - Sorting");
        System.out.println("4 - Finding max or min");
        System.out.println("5 - Find average or median");
        System.out.println("6 - Join or intersection");
        System.out.println("7 - Cut");
        System.out.println("8 - Clear");*/
        System.out.println("9 - Print");
        System.out.println("0 - Exit");
        System.out.print("\nChoose action you want: ");
    }
}
