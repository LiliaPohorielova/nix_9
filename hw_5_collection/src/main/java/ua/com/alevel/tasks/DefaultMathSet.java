package ua.com.alevel.tasks;

import org.apache.commons.lang3.math.NumberUtils;
import ua.com.alevel.ChooseTask;
import ua.com.alevel.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import static ua.com.alevel.util.InputHelper.enterNumber;
import static ua.com.alevel.util.Navigation.*;

public class DefaultMathSet {

    private static MathSet defaultMathSet;
    final Random random = new Random();

    public void run(BufferedReader input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            System.out.println("\n===================== DEFAULT MATH SET =======================");
            menuOfMathSet();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    System.out.println("\n===================== EXIT =====================");
                    break;
                }
                mathSetAction(position, reader);
            }
        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        new ChooseTask().run();
    }

    private void mathSetAction(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> add(reader);
            case "3" -> sort(reader);
            case "9" -> print(true);
            /*case "4" -> findById(reader);
            case "5" -> findAll(reader);*/
        }
        System.out.println("\n===================== DEFAULT MATH SET =======================");
        menuOfMathSet();
    }

    private void create(BufferedReader reader) {
        System.out.println("\n--------------------- CREATE DEFAULT MATH SET ---------------------");
        defaultMathSet = new MathSet();
        System.out.println("Default MathSet with capacity 10 was created");
        System.out.println("Mathset is empty..." + defaultMathSet);
    }

    private void add(BufferedReader reader) {
        try {
            String add;
            printAddMenu();
            while ((add = reader.readLine()) != null) {
                switch (add) {
                    case "1" -> {
                        System.out.println("\nEnter number (all types of numbers are available): ");
                        defaultMathSet.add(enterNumber(reader));
                        print(false);
                    }
                    case "2" -> {
                        System.out.println("\nGenerated random array of numbers: ");
                        Number[] nums = {random.nextInt(100), random.nextInt(100),
                                         random.nextInt(100), random.nextInt(100),
                                         random.nextInt(100)};
                        defaultMathSet.add(nums);
                        print(false);
                    }
                    case "0" -> {
                        run(reader);
                    }
                    default -> System.out.println("Try again");
                }
                System.out.println();
                printAddMenu();
            }

        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void sort(BufferedReader reader) {
        try {
            String sort;
            printSortMenu();
            while ((sort = reader.readLine()) != null) {
                switch (sort) {
                    case "1" -> {
                        defaultMathSet.sortAsc();
                        print(false);
                    }
                    case "2" -> {
                        System.out.print("Enter first index: ");
                        int firstIndex = Integer.parseInt(reader.readLine());
                        System.out.print("Enter last index: ");
                        int lastIndex = Integer.parseInt(reader.readLine());
                        defaultMathSet.sortAsc(firstIndex, lastIndex);
                        print(false);
                    }
                    case "3" -> {
                        System.out.println("\nCurrent Math Set: " + defaultMathSet);
                        System.out.print("Enter one number from current Math Set: ");
                        defaultMathSet.sortAsc(enterNumber(reader));
                        print(false);
                    }
                    case "4" -> {
                        defaultMathSet.sortDesc();
                        print(false);
                    }
                    case "5" -> {
                        System.out.print("Enter first index: ");
                        int firstIndex = Integer.parseInt(reader.readLine());
                        System.out.print("Enter last index: ");
                        int lastIndex = Integer.parseInt(reader.readLine());
                        defaultMathSet.sortDesc(firstIndex, lastIndex);
                        print(false);
                    }
                    case "6" -> {
                        System.out.println("\nCurrent Math Set: " + defaultMathSet);
                        System.out.print("Enter one number from current Math Set: ");
                        defaultMathSet.sortDesc(enterNumber(reader));
                        print(false);
                    }
                    case "0" -> {
                        run(reader);
                    }
                    default -> System.out.println("Try again");
                }
                printSortMenu();
            }
        } catch ( IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void print(boolean mainMenu) {
        if (mainMenu) {
            System.out.println("\n--------------------- PRINT DEFAULT MATH SET ---------------------");
            System.out.println("Your MathSet:");
        } else {
            System.out.println("\nYour MathSet:");
        }
        System.out.print(defaultMathSet);
    }
}
