package ua.com.alevel.mathSets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.ChooseTask;
import ua.com.alevel.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import static ua.com.alevel.util.InputHelper.enterNumber;
import static ua.com.alevel.util.Navigation.*;

public class ArrayMathSet {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private static MathSet arrayMathSet;
    private static MathSet randomMathSet;
    final Random random = new Random();

    public void run(BufferedReader input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            System.out.println("\n===================== CAPACITY MATH SET =======================");
            menuOfMathSet();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    System.out.println("\n===================== EXIT =====================");
                    break;
                }
                mathSetAction(position, reader);
            }
        } catch (IOException | RuntimeException e) {
            LOGGER_ERROR.error("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        new ChooseTask().run();
    }

    private void mathSetAction(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> add(reader);
            case "3" -> sort(reader);
            case "8" -> print(true);
            case "4" -> getMethods(reader);
            case "5" -> joinOrIntersection(reader);
            case "6" -> cut(reader);
            case "7" -> clear(reader);
        }
        System.out.println("\n===================== CAPACITY MATH SET =======================");
        menuOfMathSet();
    }

    private void create(BufferedReader reader) {
        System.out.println("\n--------------------- CREATE CAPACITY MATH SET ---------------------");
        LOGGER_INFO.info("Creating MathSet");
        System.out.println("Generated random array of numbers... ");
        Number[] nums = {random.nextInt(100), random.nextInt(100),
                random.nextInt(100), random.nextInt(100),
                random.nextInt(100)};
        arrayMathSet = new MathSet(nums);
        System.out.println("Your MathSet is: " + arrayMathSet);
    }

    private void add(BufferedReader reader) {
        try {
            String add;
            printAddMenu();
            while ((add = reader.readLine()) != null) {
                switch (add) {
                    case "1" -> {
                        System.out.println("\nEnter number (all types of numbers are available): ");
                        LOGGER_INFO.info("Add elements to MathSet");
                        arrayMathSet.add(enterNumber(reader));
                        print(false);
                    }
                    case "2" -> {
                        System.out.println("\nGenerated random array of numbers: ");
                        Number[] nums = {random.nextInt(100), random.nextInt(100),
                                random.nextInt(100), random.nextInt(100),
                                random.nextInt(100)};
                        LOGGER_INFO.info("Add elements to MathSet");
                        arrayMathSet.add(nums);
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
            LOGGER_ERROR.error("Error: " + e.getMessage());
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
                        LOGGER_INFO.info("Sorting MathSet");
                        arrayMathSet.sortAsc();
                        print(false);
                    }
                    case "2" -> {
                        System.out.print("Enter first index: ");
                        int firstIndex = Integer.parseInt(reader.readLine());
                        System.out.print("Enter last index: ");
                        int lastIndex = Integer.parseInt(reader.readLine());
                        LOGGER_INFO.info("Sorting MathSet");
                        arrayMathSet.sortAsc(firstIndex, lastIndex);
                        print(false);
                    }
                    case "3" -> {
                        System.out.println("\nCurrent Math Set: " + arrayMathSet);
                        System.out.print("Enter one number from current Math Set: ");
                        LOGGER_INFO.info("Sorting MathSet");
                        arrayMathSet.sortAsc(enterNumber(reader));
                        print(false);
                    }
                    case "4" -> {
                        LOGGER_INFO.info("Sorting MathSet");
                        arrayMathSet.sortDesc();
                        print(false);
                    }
                    case "5" -> {
                        System.out.print("Enter first index: ");
                        int firstIndex = Integer.parseInt(reader.readLine());
                        System.out.print("Enter last index: ");
                        int lastIndex = Integer.parseInt(reader.readLine());
                        LOGGER_INFO.info("Sorting MathSet");
                        arrayMathSet.sortDesc(firstIndex, lastIndex);
                        print(false);
                    }
                    case "6" -> {
                        System.out.println("\nCurrent Math Set: " + arrayMathSet);
                        System.out.print("Enter one number from current Math Set: ");
                        LOGGER_INFO.info("Sorting MathSet");
                        arrayMathSet.sortDesc(enterNumber(reader));
                        print(false);
                    }
                    case "0" -> {
                        run(reader);
                    }
                    default -> System.out.println("Try again");
                }
                printSortMenu();
            }
        } catch (IOException | RuntimeException e) {
            LOGGER_ERROR.error("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getMethods(BufferedReader reader) {
        try {
            String add;
            printGetMenu();
            while ((add = reader.readLine()) != null) {
                switch (add) {
                    case "1" -> {
                        System.out.println("\nCurrent Math Set: " + arrayMathSet);
                        System.out.print("Maximal element of current Math Set: ");
                        System.out.println(arrayMathSet.getMax());
                    }
                    case "2" -> {
                        System.out.println("\nCurrent Math Set: " + arrayMathSet);
                        System.out.print("Minimal element of current Math Set: ");
                        System.out.println(arrayMathSet.getMin());
                    }
                    case "3" -> {
                        System.out.println("\nCurrent Math Set: " + arrayMathSet);
                        System.out.print("Average element of current Math Set: ");
                        System.out.println(arrayMathSet.getAverage());
                    }
                    case "4" -> {
                        System.out.println("\nCurrent Math Set: " + arrayMathSet);
                        System.out.print("Median of current Math Set: ");
                        System.out.println(arrayMathSet.getMedian());
                    }
                    case "5" -> {
                        System.out.println("\nCurrent Math Set: " + arrayMathSet);
                        System.out.print("Enter index of one element from current Math Set: ");
                        int index = Integer.parseInt(reader.readLine());
                        System.out.println("Your number is: " + arrayMathSet.getNumber(index));
                    }
                    case "0" -> {
                        run(reader);
                    }
                    default -> System.out.println("Try again");
                }
                System.out.println();
                printGetMenu();
            }

        } catch (IOException | RuntimeException e) {
            LOGGER_ERROR.error("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void joinOrIntersection(BufferedReader reader) {
        try {
            String add;
            printJoinOrInterMenu();
            while ((add = reader.readLine()) != null) {
                switch (add) {
                    case "1" -> {
                        System.out.println("\nCurrent Math Set: " + arrayMathSet);
                        randomMathSet = new MathSet();
                        Number[] nums = {random.nextInt(100), random.nextInt(100),
                                random.nextInt(100), random.nextInt(100),
                                random.nextInt(100)};
                        randomMathSet.add(nums);
                        System.out.println("Random Math Set: " + randomMathSet);
                        System.out.print("Joining result: ");
                        arrayMathSet.join(randomMathSet);
                        System.out.println(arrayMathSet);
                    }
                    case "2" -> {
                        System.out.println("\nCurrent Math Set: " + arrayMathSet);
                        randomMathSet = new MathSet();
                        Number[] nums = {random.nextInt(100), random.nextInt(100),
                                random.nextInt(100), random.nextInt(100),
                                random.nextInt(100)};
                        randomMathSet.add(nums);
                        System.out.println("Random Math Set: " + randomMathSet);
                        System.out.print("Intersection result: ");
                        arrayMathSet.intersection(randomMathSet);
                        System.out.println(arrayMathSet);
                    }
                    case "0" -> {
                        run(reader);
                    }
                    default -> System.out.println("Try again");
                }
                System.out.println();
                printJoinOrInterMenu();
            }

        } catch (IOException | RuntimeException e) {
            LOGGER_ERROR.error("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cut(BufferedReader reader) {
        try {
            System.out.println("\n--------------------- CUTTING ----------------------");
            System.out.println("\nCurrent Math Set: " + arrayMathSet);
            System.out.print("Enter first index: ");
            int firstIndex = Integer.parseInt(reader.readLine());
            System.out.print("Enter last index: ");
            int lastIndex = Integer.parseInt(reader.readLine());
            LOGGER_WARN.warn("Cutting MathSet");
            System.out.println("\nCutting Math Set: " + arrayMathSet.cut(firstIndex, lastIndex));
        } catch (IOException | RuntimeException e) {
            LOGGER_ERROR.error("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void clear(BufferedReader reader) {
        try {
            System.out.println("\n--------------------- CLEANING ----------------------");
            System.out.println("\nCurrent Math Set: " + arrayMathSet);
            System.out.println("Clear math set...");
            LOGGER_WARN.warn("Clearing MathSet");
            arrayMathSet.clear();
            System.out.println("Current Math Set: Empty..." + arrayMathSet);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("Error: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void print(boolean mainMenu) {
        if (mainMenu) {
            System.out.println("\n--------------------- PRINT CAPACITY MATH SET ---------------------");
            System.out.println("Your MathSet:");
        } else {
            System.out.println("\nYour MathSet:");
        }
        System.out.print(arrayMathSet);
    }
}
