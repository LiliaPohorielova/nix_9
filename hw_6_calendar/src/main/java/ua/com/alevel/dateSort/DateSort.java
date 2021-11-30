package ua.com.alevel.dateSort;

import ua.com.alevel.ChooseTask;
import ua.com.alevel.date.CustomDateList;

import java.io.BufferedReader;

import static ua.com.alevel.date.CustomDateList.printList;
import static ua.com.alevel.date.CustomDateList.sortList;

public class DateSort {

    public static void run(BufferedReader input) {
        System.out.println("------------------ SORT DATES ------------------");
        try {
            System.out.println("You have such dates:");
            printList();
            sortList();
            printList();
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        new ChooseTask().run();
    }
}
