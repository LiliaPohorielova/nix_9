package ua.com.alevel.tasks;

import ua.com.alevel.ChooseTask;

import java.io.BufferedReader;
import java.io.IOException;

public class SumOfNumbers {

    public static void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("\n===================== FIRST TASK =====================");

        do {
            System.out.print("Input your string: ");
            String str = input.readLine();

            int sum = 0;
            if (str.matches(".*\\d+.*")) { //str cодержит цифры
                for (int i = 0; i < str.length(); i++) {
                    if (Character.isDigit(str.charAt(i))) {
                        sum += Integer.parseInt(String.valueOf(str.charAt(i)));
                    }
                }
                System.out.println("The sum of the numbers is " + sum);
            } else {
                System.out.println("Your string does not contain digits");
            }

            System.out.println("\nDo you want to continue? (Y/N)");
            again = input.readLine();
            System.out.println();

        } while (again.equalsIgnoreCase("Y"));

        new ChooseTask().run();
    }
}
