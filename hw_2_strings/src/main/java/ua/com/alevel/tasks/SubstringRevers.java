package ua.com.alevel.tasks;

import ua.com.alevel.ChooseTask;
import ua.com.alevel.StringHelperUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class SubstringRevers {

    public static void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("\n===================== SUBSTRING REVERSE =====================");

        do {
            System.out.print("Input your string: ");
            String str = input.readLine();
            System.out.print("Input substring: ");
            String substring = input.readLine();

            str = StringHelperUtil.reverse(str,substring);
            System.out.println(str);

            System.out.println("\nDo you want to continue? (Y/N)");
            again = input.readLine();
            System.out.println();


        } while (again.equalsIgnoreCase("Y"));

        new ChooseTask().run();
    }
}
