package ua.com.alevel.dateAdd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.ChooseTask;

import static ua.com.alevel.date.CustomDateList.printList;
import static ua.com.alevel.dateFormat.InputDateFormat.selectDataFormat;
import static ua.com.alevel.util.Navigation.printInputDateFormatMenu;

public class AddNewDate {

    private static final Logger INFO = LoggerFactory.getLogger("info");
    private static final Logger ERROR = LoggerFactory.getLogger("error");

    public static void run() {
        INFO.info("Add new date");
        System.out.println("\n=================================================== ADD NEW DATE ========================================================");
        try {
            printInputDateFormatMenu();
            selectDataFormat();
            System.out.println("Your list of Dates: ");
            printList();
        } catch (RuntimeException e) {
            ERROR.error(e.getMessage());
            System.out.println("Problem: " + e.getMessage());
        }
        new ChooseTask().run();
    }
}
