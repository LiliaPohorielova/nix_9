package ua.com.alevel.dateAdd;

import ua.com.alevel.ChooseTask;

import static ua.com.alevel.date.CustomDateList.printList;
import static ua.com.alevel.dateFormat.InputDateFormat.selectDataFormat;
import static ua.com.alevel.util.Navigation.printInputDateFormatMenu;

public class AddNewDate {

    public static void run() {
        System.out.println("\n=================================================== ADD NEW DATE ========================================================");
        try {
            printInputDateFormatMenu();
            selectDataFormat();
            System.out.println("Your list of Dates: ");
            printList();
        } catch (RuntimeException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        new ChooseTask().run();
    }
}
