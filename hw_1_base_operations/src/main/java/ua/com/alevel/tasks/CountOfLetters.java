package ua.com.alevel.tasks;

import ua.com.alevel.ChooseTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class CountOfLetters {

    public static void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("\n===================== SECOND TASK =====================");

        do {
            System.out.print("Input your string: ");
            String str = input.readLine().trim().toLowerCase();

            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            for (int i = 0; i < chars.length; i += (s.lastIndexOf(chars[i]) - s.indexOf(chars[i]) + 1)) {
                if (Character.isLetter(chars[i])) {
                    System.out.println(chars[i] + "-" + (s.lastIndexOf(chars[i]) - s.indexOf(chars[i]) + 1));
                }
            }

            System.out.println("\nDo you want to continue? (Y/N)");
            again = input.readLine();
            System.out.println();

        } while (again.equalsIgnoreCase("Y"));

        new ChooseTask().run();
    }

}

