package ua.com.alevel.date;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ua.com.alevel.dateValid.FormatVerify.isValidDate;
import static ua.com.alevel.util.ParseDate.dateToMilliseconds;
import static ua.com.alevel.util.ParseDate.millisecondsToDate;

public class CustomDateList extends ArrayList<CustomDate> {

    public static List<CustomDate> dateList = new ArrayList<>();
    private static List<Long> milliList = new ArrayList<>();

    public static List<CustomDate> getDateList() {
        return dateList;
    }

    public static void addDate(CustomDate customDate) throws NumberFormatException {
        if (isValidDate(customDate)) dateList.add(customDate);
    }

    public static void printList() {
        if (!dateList.isEmpty()) {
            for (CustomDate d : dateList) {
                System.out.println(d);
            }
        } else {
            System.out.println("Your list of Dates is empty!");
        }
    }

    public static void sortDateList(boolean asc) {
        if (dateList.isEmpty()) {
            System.out.println("No dates to sort.");
            return;
        }
        dateList.stream().forEach(x -> milliList.add(dateToMilliseconds(x)));
        if (asc) Collections.sort(milliList);
        else Collections.sort(milliList, Collections.reverseOrder());
        dateList.clear();
        milliList.stream().forEach(x -> dateList.add(millisecondsToDate(x)));
        milliList.clear();
        dateList.stream().forEach(x -> System.out.println(x));
    }

    public List<CustomDate> findAll() {
        return dateList;
    }

    public static CustomDate findDateById(BufferedReader reader) {
        String inputId;
        try {
            while ((inputId = reader.readLine()) != null) {
                CustomDate foundDate = dateList.get(Integer.parseInt(inputId));
                if (foundDate == null) {
                    System.out.println("Not exist such date, try again please!");
                } else {
                    return foundDate;
                }
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Problem = " + e.getMessage());
        }
        throw new RuntimeException("Date is not found!");
    }
}
