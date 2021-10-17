package ua.com.alevel.tasks;

import ua.com.alevel.ChooseTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Timetable {

    public static void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("\n===================== THIRD TASK =====================");

        do {
            String str;
            int numOfLesson, endTime;
            int firstLesson = 9 * 60, durationOfLesson = 45, bigPause = 15, smallPause = 5;
            System.out.print("Enter the lesson number: ");
            str = input.readLine();

            while (check(str) == false) {
                System.out.println("Incorrect value. Please, try again.");
                System.out.print("\nEnter the lesson number: ");
                input = new BufferedReader(new InputStreamReader(System.in));
                str = input.readLine();
            }

            numOfLesson = Integer.parseInt(str);
            endTime = firstLesson + numOfLesson * durationOfLesson;
            endTime += (numOfLesson - 1) * smallPause;
            endTime += (numOfLesson - 1) / 2 * (bigPause - smallPause);

            System.out.printf("This lesson ends at: ");
            System.out.println(endTime / 60 + " " + endTime % 60);

            System.out.println("\nDo you want to continue? (Y/N)");
            again = input.readLine();
            System.out.println();

        } while (again.equalsIgnoreCase("Y"));

        new ChooseTask().run();
    }

    public static boolean check(String in) {
        char[] arr = in.toCharArray();
        for (char c : arr) {
            if (!Character.isDigit(c)) return false;
        }
        int x = Integer.parseInt(in);
        if (x < 1 || x > 10) return false;
        return true;
    }

}
