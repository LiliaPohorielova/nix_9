package ua.com.alevel;

import ua.com.alevel.tasks.SumOfNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChooseTask {
    public static void run(){
        helper();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        try {
            while((choice = input.readLine()) != null){
                switch(choice){
                    case "1": {
                        new SumOfNumbers().run(input);
                        break;
                    }
                    /*case "2": {
                        new SecondTask().run(input);
                        break;
                    }
                    case "3": {
                        new ThirdTask().run(input);
                        break;
                    }*/
                    case "0": {
                        System.exit(0);
                    } break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void helper(){

        System.out.println("=================MAIN MENU=================");
        System.out.println("Main menu");
        System.out.println("Enter the task number to run it.");
        System.out.println("Enter 'help' to show some instructions");
        System.out.println("Enter 0 to exit the program.");
        System.out.print("\nTask number you want: ");
        //Добавить описание к каждой задаче
    }
}
