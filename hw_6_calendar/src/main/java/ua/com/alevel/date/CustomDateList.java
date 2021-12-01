package ua.com.alevel.date;

import ua.com.alevel.dateCompare.DifferenceBetweenDates;
import ua.com.alevel.dateFormat.OutputDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ua.com.alevel.dateValid.FormatVerify.isValidDate;

public class CustomDateList extends ArrayList<CustomDate> {

    public static List<CustomDate> dateList = new ArrayList<>();
    private static List<Long> milliList = new ArrayList<>();
    public static int idOfDateList;

    public static List<CustomDate> getDateList() {
        return dateList;
    }

    public static void addDate(CustomDate customDate) throws NumberFormatException {
        if (isValidDate(customDate)) dateList.add(customDate);
    }

    public static void printList() {
        if (!dateList.isEmpty()) {
            System.out.println("Your list of Dates: ");
            for (CustomDate d : dateList) {
                System.out.println(d);
            }
        } else {
            System.out.println("Your list of Dates is empty!");
        }
    }

    public static void sortList() {
        if (dateList.isEmpty()) {
            System.out.println("No dates to sort.");
            return;
        }
        dateList.stream().forEach(x -> milliList.add(DifferenceBetweenDates.dateToMilliseconds(x)));
        Collections.sort(milliList);
        dateList.clear();
        milliList.stream().forEach(x -> dateList.add(DifferenceBetweenDates.millisecondsToDate(x)));
        milliList.clear();
        dateList.stream().forEach(x -> OutputDateFormat.dateOutput(x));
        printList();
    }

    public List<CustomDate> findAll() {
        return dateList;
    }
}
