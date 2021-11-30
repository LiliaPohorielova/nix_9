package ua.com.alevel.dateAdd;

import ua.com.alevel.ChooseTask;
import ua.com.alevel.date.CustomDate;
import ua.com.alevel.date.CustomDateList;
import ua.com.alevel.dateFormat.InputDateFormat;
import ua.com.alevel.util.StringToDate;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.com.alevel.date.CustomDateList.printList;
import static ua.com.alevel.dateFormat.InputDateFormat.selectDataFormat;
import static ua.com.alevel.util.Navigation.printFormatMenu;

public class AddNewDate {

    public static void run() {
        System.out.println("\n------------------ ADD NEW DATE ------------------");
        try {
            selectDataFormat();
            printList();
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        new ChooseTask().run();
    }
}
