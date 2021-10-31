package ua.com.alevel.level2;

import ua.com.alevel.ChooseTask;

import java.io.IOException;
import java.util.Scanner;

public class CheckBrackets {

    public static void run(Scanner scanner) throws IOException {
        String again = "";
        int length = 0;
        System.out.println("\n---------------------- Checking Brackets ---------------------");
        do {
            System.out.print("Input yor string with brackets: ");
            boolean isCorrect = false;
            String s = scanner.nextLine().trim();
            while (true) {
                if (s.contains("()")) {
                    s = s.replace("()", "");
                } else if (s.contains("[]")) {
                    s = s.replace("[]", "");
                } else if (s.contains("{}")) {
                    s = s.replace("{}", "");
                } else if (s.length() > 0) {
                    isCorrect = false;
                    break;
                } else {
                    isCorrect = true;
                    break;
                }
            }
            if (isCorrect) {
                System.out.println("This string is correct");
            } else {
                System.out.println("This string is NOT correct");
            }

            System.out.println("\nDo you want to continue? (Y/N)");
            again = scanner.nextLine();
            System.out.println();

        } while (again.equalsIgnoreCase("Y"));
        new ChooseTask().level2(scanner);
    }
}
