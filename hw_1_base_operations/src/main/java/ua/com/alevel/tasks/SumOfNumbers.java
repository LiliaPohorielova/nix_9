package ua.com.alevel.tasks;

import ua.com.alevel.ChooseTask;

import java.io.BufferedReader;
import java.io.IOException;

public class SumOfNumbers {
    public static void run(BufferedReader input) throws IOException {
        System.out.println("\n=================FIRST TASK=================");
        //System.out.println("SumOfNumbers.run");
        System.out.println("Input your string: ");
        String str = input.readLine();

        int sum=0;
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

        System.out.println("Press Enter to continue ...");
        System.in.read();
        new ChooseTask().run();
    }
}
