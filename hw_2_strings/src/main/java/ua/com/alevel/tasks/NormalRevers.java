package ua.com.alevel.tasks;

import ua.com.alevel.ChooseTask;
import ua.com.alevel.StringHelperUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class NormalRevers {

    public static void run(BufferedReader input) throws IOException {
    String again = "";
        System.out.println("\n===================== NORMAL REVERSE =====================");

        do {
        System.out.print("Input your string: ");
        String str = input.readLine();

        //todo "Do you want to reverse full string?"
        str = StringHelperUtil.reverse(str, true);
        System.out.println(str);
        str = StringHelperUtil.reverse(str, true);
        str = StringHelperUtil.reverse(str, false);
        System.out.println(str);

        System.out.println("\nDo you want to continue? (Y/N)");
        again = input.readLine();
        System.out.println();

    } while (again.equalsIgnoreCase("Y"));

        new ChooseTask().run();
    }
}
