package ua.com.alevel;

import ua.com.alevel.dateAdd.AddNewDate;
import ua.com.alevel.dateChange.DecreaseDate;
import ua.com.alevel.dateChange.IncreaseDate;
import ua.com.alevel.dateClear.ClearAllDates;
import ua.com.alevel.dateCompare.DifferenceBetweenDates;
import ua.com.alevel.datePrint.PrintListOfDates;
import ua.com.alevel.dateSort.DateSort;

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
                    case "3" -> new DateSort().run(input);
                    case "4" -> new DifferenceBetweenDates().run(input);
                    case "5" -> new IncreaseDate().run(input);
                    case "6" -> new DecreaseDate().run(input);
                    case "7" -> new ClearAllDates().run();
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
            System.out.println("Problem: " + e.getMessage());
        }
    }
}
