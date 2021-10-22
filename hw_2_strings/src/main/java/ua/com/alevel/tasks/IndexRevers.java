package ua.com.alevel.tasks;

import ua.com.alevel.ChooseTask;
import ua.com.alevel.StringHelperUtil;

import java.io.BufferedReader;
import java.io.IOException;

public class IndexRevers {

    public static void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("\n===================== INDEX REVERSE =====================");

        do {
            System.out.print("Input your string: ");
            String str = input.readLine();
            System.out.print("Enter first index: ");
            int firstIndex = Integer.parseInt(input.readLine());
            System.out.print("Enter last index: ");
            int lastIndex = Integer.parseInt(input.readLine());
            if (firstIndex >= lastIndex || lastIndex > str.length() || firstIndex < 0) {
                System.out.println("Enter correct index");
                return;
            }
            str = StringHelperUtil.reverse(str, firstIndex, lastIndex);
            System.out.println(str);

            System.out.println("\nDo you want to continue? (Y/N)");
            again = input.readLine();
            System.out.println();

        } while (again.equalsIgnoreCase("Y"));

        new ChooseTask().run();
    }
}
