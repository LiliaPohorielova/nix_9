package ua.com.alevel.tasks;

import ua.com.alevel.ChooseTask;
import ua.com.alevel.MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.com.alevel.util.Navigation.menuOfMathSet;

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
                    System.out.println("\n-------------------- EXIT ---------------------");
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
        System.out.println("\n===================== CREATE DEFAULT MATH SET =====================");
        defaultMathSet = new MathSet();
        System.out.println("Default MathSet with capacity 10 was created");
        System.out.println("Mathset is empty..." + defaultMathSet);
    }

    private void add(BufferedReader reader) {
        System.out.println("\n===================== ADD ELEMENTS DEFAULT MATH SET =====================");
        try {
            String add;
            System.out.println("1 - Add element");
            System.out.println("2 - Add elements");
            System.out.println("0 - Exit");
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
                        System.out.println("\n-------------------- EXIT ---------------------");
                        run(reader);
                    }
                    default -> System.out.println("Try again");
                }
                System.out.println("\n1 - Add element");
                System.out.println("2 - Add elements");
                System.out.println("0 - Exit");
            }

        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void sort(BufferedReader reader) {
        System.out.println("\n===================== SORT ELEMENTS DEFAULT MATH SET =====================");
        try {
            String sort;
            System.out.println("1 - Sort Ascending");
            System.out.println("2 - Sort Ascending Part of Math Set");
            System.out.println("3 - Sort Descending");
            System.out.println("4 - Sort Descending Part of Math Set");
            System.out.println("0 - Exit");
            while ((sort = reader.readLine()) != null) {
                switch (sort) {
                    case "1" -> {
                        defaultMathSet.sortAsc();
                        print(false);
                    }
                    case "2" -> {
                        System.out.println("Enter first index");
                        System.out.println("Enter last index");
                        //TODO
                        defaultMathSet.sortAsc(1,3);
                        print(false);
                    }
                    case "3" -> {
                        defaultMathSet.sortDesc();
                        print(false);
                    }
                    case "4" -> {
                        //TODO
                        defaultMathSet.sortDesc(1,3);
                        print(false);
                    }
                    case "0" -> {
                        System.out.println("\n-------------------- EXIT ---------------------");
                        run(reader);
                    }
                    default -> System.out.println("Try again");
                }
                System.out.println("\n1 - Sort Ascending");
                System.out.println("2 - Sort Ascending Part of Math Set");
                System.out.println("3 - Sort Descending");
                System.out.println("4 - Sort Descending Part of Math Set");
                System.out.println("0 - Exit");
            }

        } catch (IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void print(boolean mainMenu) {
        if(mainMenu) {
            System.out.println("\n===================== PRINT DEFAULT MATH SET =====================");
        } else {
            System.out.println("Your MathSet:");
        }
        System.out.println(defaultMathSet);
    }
}
