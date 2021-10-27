package ua.com.alevel.level1;

import ua.com.alevel.ChooseTask;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChessHorse {

    public static void run(Scanner scanner) throws IOException {
        String again = "";
        int xStart = 0, yStart = 0;
        int xEnd = 0, yEnd = 0;
        System.out.println("\n---------------------- Chess Horse ---------------------");
        do {
            while (true) {
                try {
                    System.out.println("Input start position:");
                    System.out.print("X: ");
                    xStart = scanner.nextInt();
                    System.out.print("Y: ");
                    yStart = scanner.nextInt();
                    System.out.println("\nInput end position");
                    System.out.print("X: ");
                    xEnd = scanner.nextInt();
                    System.out.print("Y: ");
                    yEnd = scanner.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect input\n");
                    scanner.nextLine();
                    continue;
                }
                break;
            }
            int distX = Math.abs(xStart - xEnd);
            int distY = Math.abs(yStart - yEnd);
            if (((distX == 1 && distY == 2)) || ((distX == 2) && (distY == 1))) {
                System.out.println("\nResult\nChess horse can move to this point");
            } else {
                System.out.println("\nResult\nChess horse can NOT move to this point");
            }
            scanner.nextLine();
            System.out.println("\nDo you want to continue? (Y/N)");
            again = scanner.nextLine();
            System.out.println();

        } while (again.equalsIgnoreCase("Y"));
        new ChooseTask().level1(scanner);
    }
}
