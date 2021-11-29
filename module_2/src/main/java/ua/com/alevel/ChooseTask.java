package ua.com.alevel;

import ua.com.alevel.date.DateFormat;
import ua.com.alevel.names.FirstUniqueName;
import ua.com.alevel.salesman.TravellingSalesmanProblem;

import java.io.IOException;
import java.util.Scanner;

public class ChooseTask {

    public static void run() {
        helper();
        Scanner scanner = new Scanner(System.in);
        String choiceLevel;
        try {
            while ((choiceLevel = scanner.nextLine()) != null) {
                switch (choiceLevel) {
                    case "1":
                        new DateFormat().run();
                        break;
                    case "2":
                        new FirstUniqueName().run();
                        break;
                    case "3":
                        new TravellingSalesmanProblem().run();
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
        System.out.println("\n===================== MENU OF MODULE 2 =======================");
        System.out.println("Choose task number: ");
        System.out.println("1 - Task 1");
        System.out.println("2 - Task 2");
        System.out.println("3 - Task 3");
        System.out.println("0 - Exit");
        System.out.print("\nLevel number you want: ");
    }
}
