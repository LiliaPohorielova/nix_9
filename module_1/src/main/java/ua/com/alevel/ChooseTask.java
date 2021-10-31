package ua.com.alevel;

import ua.com.alevel.level1.AreaOfTriangle;
import ua.com.alevel.level1.ChessHorse;
import ua.com.alevel.level1.UniqueSymbols;
import ua.com.alevel.level2.BinaryTree;
import ua.com.alevel.level2.CheckBrackets;
import ua.com.alevel.level3.GameOfLife;

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
                        level1(scanner);
                        break;
                    case "2":
                        level2(scanner);
                        break;
                    case "3":
                        level3(scanner);
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
        System.out.println("\n===================== MENU OF MODULE 1 =======================");
        System.out.println("Choose level: ");
        System.out.println("1 - Level 1");
        System.out.println("2 - Level 2");
        System.out.println("3 - Level 3");
        System.out.println("0 - Exit");
        System.out.print("\nLevel number you want: ");
    }

    public static void level1(Scanner scanner) throws IOException {
        System.out.println("\n===================== LEVEL 1 =====================");
        System.out.println("Choose task: ");
        System.out.println("1 - Count unique numbers of array");
        System.out.println("2 - Find position of chess horse");
        System.out.println("3 - Calculate the area of triangle");
        System.out.println("0 - Exit to main menu");
        System.out.print("\nTask number you want: ");

        String choice;
        try {
            while ((choice = scanner.nextLine()) != null) {
                switch (choice) {
                    case "1":
                        new UniqueSymbols().run(scanner);
                        break;
                    case "2":
                        new ChessHorse().run(scanner);
                        break;
                    case "3":
                        new AreaOfTriangle().run(scanner);
                        break;
                    case "0":
                        new ChooseTask().run();
                        return;
                    default:
                        System.out.println("Incorrect value. Please, try again.");
                        System.out.print("\nTask number you want: ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void level2(Scanner scanner) throws IOException {
        System.out.println("\n===================== LEVEL 2 =====================");
        System.out.println("Choose task: ");
        System.out.println("1 - Check for correct of brackets in a string");
        System.out.println("2 - Find the maximum depth of a binary tree");
        System.out.println("0 - Exit to main menu");
        System.out.print("\nTask number you want: ");

        String choice;
        try {
            while ((choice = scanner.nextLine()) != null) {
                switch (choice) {
                    case "1":
                        new CheckBrackets().run(scanner);
                        break;
                    case "2":
                        new BinaryTree().run(scanner);
                        break;
                    case "0":
                        new ChooseTask().run();
                        return;
                    default:
                        System.out.println("Incorrect value. Please, try again.");
                        System.out.print("\nTask number you want: ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void level3(Scanner scanner) throws IOException {
        System.out.println("\n===================== LEVEL 3 =====================");
        System.out.println("Choose option \"Game of Life\": ");
        System.out.println("1 - Random grid");
        System.out.println("2 - Toad grid");
        System.out.println("0 - Exit to main menu");
        System.out.print("\nTask number you want: ");

        String choice;
        try {
            while ((choice = scanner.nextLine()) != null) {
                switch (choice) {
                    case "1":
                        new GameOfLife().run(scanner, false);
                        break;
                    case "2":
                        new GameOfLife().run(scanner, true);
                        break;
                    case "0":
                        new ChooseTask().run();
                        return;
                    default:
                        System.out.println("Incorrect value. Please, try again.");
                        System.out.print("\nTask number you want: ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
