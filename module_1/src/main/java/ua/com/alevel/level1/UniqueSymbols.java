package ua.com.alevel.level1;

import ua.com.alevel.ChooseTask;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UniqueSymbols {

    public static void run(Scanner scanner) throws IOException {
        String again = "";
        int length = 0;
        System.out.println("\n---------------------- Unique Symbols ---------------------");
        do {
            while (true) {
                System.out.print("Input size of array: ");
                try {
                    length = scanner.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect input\n");
                    scanner.nextLine();
                    continue;
                }
                break;
            }

            int[] array;
            while (true) {
                try {
                    array = new int[length];
                    System.out.print("Input array of integer numbers: ");
                    for (int i = 0; i < length; i++) {
                        array[i] = scanner.nextInt();
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect input\n");
                    scanner.nextLine();
                    continue;
                }
                break;
            }
            scanner.nextLine();
            int[] copy = new int[array.length];
            int res = 0;
            int countNum = 0;
            int count = 0;

            for (int i = 0; i < array.length; i++) {
                if (copy[i] == 0) {
                    for (int j = 0; j < array.length; j++) {
                        if (array[i] == array[j]) {
                            copy[j] = 1;
                            count++;
                        }
                    }
                }
                if (countNum < count) {
                    countNum = count;
                    res++;
                }

            }
            System.out.println("Counts of unique numbers: " + res);

            System.out.println("\nDo you want to continue? (Y/N)");
            again = scanner.nextLine();
            System.out.println();

        } while (again.equalsIgnoreCase("Y"));
        new ChooseTask().level1(scanner);
    }
}
