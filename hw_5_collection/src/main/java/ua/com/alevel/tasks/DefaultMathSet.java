package ua.com.alevel.tasks;

import org.apache.commons.lang3.math.NumberUtils;
import ua.com.alevel.ChooseTask;
import ua.com.alevel.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.com.alevel.util.Navigation.*;

public class DefaultMathSet {

    private static MathSet defaultMathSet;

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
                        Number num = Math.round(Math.random() * 20);
                        defaultMathSet.add(num);
                        print(false);
                    }
                    case "2" -> {
                        Number[] nums = {Math.round(Math.random() * 20), Math.round(Math.random() * 20), Math.round(Math.random() * 20)};
                        defaultMathSet.add(nums);
                        print(false);
                    }
                    case "0" -> {
                        System.out.println("\n-------------------- Exit ---------------------");
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
                        /*System.out.print("Enter one number from current Math Set: ");
                        Number num = Integer.parseInt(reader.readLine());
                        defaultMathSet.sortAsc(firstIndex, lastIndex);
                        print(false);*/
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
                    case "0" -> {
                        System.out.println("\n-------------------- Exit ---------------------");
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

    public static void enterNumber(BufferedReader reader) {
        System.out.print("Enter number: ");
        String value = null;
        Number number = 0;
        try {
            value = reader.readLine();
            number = NumberUtils.createNumber(value);
        } catch (NumberFormatException | IOException e) {
            System.out.println("Error: "+ e.getMessage());
        }
        System.out.println("Your number is: "+ number);
        System.out.println("Your class is: "+ number.getClass().getName());
    }

}
