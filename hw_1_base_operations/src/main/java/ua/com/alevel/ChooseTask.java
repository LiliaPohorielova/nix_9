package ua.com.alevel;

import ua.com.alevel.tasks.CountOfLetters;
import ua.com.alevel.tasks.SumOfNumbers;
import ua.com.alevel.tasks.Timetable;

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
                        new SumOfNumbers().run(input);
                        break;
                    case "2":
                        new CountOfLetters().run(input);
                        break;
                    case "3":
                        new Timetable().run(input);
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
        System.out.println("1 - Count the sum of the number in the string");
        System.out.println("2 - Counting letters in a string");
        System.out.println("3 - Timetable");
        System.out.println("0 - Exit");
        System.out.print("\nTask number you want: ");
        //Добавить описание к каждой задаче
    }

}
