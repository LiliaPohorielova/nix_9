package ua.com.alevel.tasks;

import ua.com.alevel.ChooseTask;
import ua.com.alevel.MathSet;

import java.io.BufferedReader;
import java.io.IOException;

public class CreateMathSet {

    public void run(BufferedReader input) throws IOException {
        String again = "";
        System.out.println("\n===================== CREATE MATH SET =====================");

        do {
            //System.out.println("1 - Please enter the capacity of the MathSet");
            MathSet<Integer> integerMyList = new MathSet<>(new MathSet<>(new Integer[]{1, 2, 3, 4, 3, Math.toIntExact(4L)}));
            MathSet<Integer> intrMyList = new MathSet<>(new Integer[]{1,4});
            Number[] num = {1, 2L, 3F, 4.0};

            System.out.println(integerMyList);
            System.out.println(intrMyList);

            System.out.println("\nDo you want to continue? (Y/N)");
            again = input.readLine();
            System.out.println();

        } while (again.equalsIgnoreCase("Y"));

        new ChooseTask().run();
    }
}
