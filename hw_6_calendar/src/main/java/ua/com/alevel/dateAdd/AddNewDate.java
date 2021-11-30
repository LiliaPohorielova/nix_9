package ua.com.alevel.dateAdd;

import ua.com.alevel.ChooseTask;
import ua.com.alevel.date.CustomDate;
import ua.com.alevel.date.CustomDateList;
import ua.com.alevel.dateFormat.InputDateFormat;
import ua.com.alevel.util.StringToDate;

import java.io.BufferedReader;
import java.io.IOException;

import static ua.com.alevel.util.Navigation.printFormatMenu;

public class AddNewDate {

    public static void run(BufferedReader input) {
        System.out.println("------------------ ADD NEW DATE ------------------");
        InputDateFormat.run(input);
        System.out.println("Please enter your date in format 10/12/2021 12:12:12:12");
        try {
            /*CustomDate customDate = StringToDate.stringToDate(input.readLine());
            dateList*/
            String string = "";
            CustomDateList.createDateList(input);
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        new ChooseTask().run();
    }
}
