package ua.com.alevel;

import ua.com.alevel.tasks.*;

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
                        new NormalRevers().run(input);
                        break;
                    case "2":
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
                        break;
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
        System.out.println("Tasks: ");
        System.out.println("1 - Normal reverse (by words and by character)");
        System.out.println("2 - Reverse by substring in the string");
        System.out.println("3 - Reverse by indexes");
        System.out.println("4 - Reverse by symbols");
        System.out.println("5 - Reverse by strings");
        System.out.println("0 - Exit");
        System.out.print("\nTask number you want: ");
    }


}
