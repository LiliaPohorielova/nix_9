package ua.com.alevel.date;

import ua.com.alevel.dateCompare.DifferenceBetweenDates;
import ua.com.alevel.dateFormat.OutputDateFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomDateList {

/*    private final List<CustomDate> dateList;

    public CustomDateList() {
        this.dateList = new ArrayList<>();;
    }*/

    public static List<CustomDate> dateList = new ArrayList<>();
    private static List<Long> milliList = new ArrayList<>();
    public static int idOfDateList;

    //public static void createCalendarFirstFormat(BufferedReader reader) {
    public static void createDateList(BufferedReader inputString) {
        //ВЫНЕСТИ И ВЫЗЫВАТЬ ИЗ СТРИНГ ТУ ДАТЕ
        String line = null;
        try {
            line = inputString.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] splitString = line.split(" ");
            int nums[] = new int[7];
            for (int i = 0; i < 7; i++) {
                nums[i] = Integer.parseInt(splitString[i]);
            }
            //TODO: ПЕРЕДЕЛАТЬ! + ДОБАВИТЬ ПРОВЕРКУ ВАЛИДНОСТИ
            CustomDate date = new CustomDate(nums[0],nums[1],nums[2],nums[3],nums[4],nums[5],nums[6]);
            dateList.add(date);
            printList();

            //idOfDateList = myCalendarList.indexOf(...);
            //выбор формата вывода даты
    }

    public List<CustomDate> getDateList() {
        return dateList;
    }

    public void addDate(CustomDate customDate) {
        dateList.add(customDate);
    }

    public static void printList() {
        for (CustomDate d: dateList) {
            System.out.println(d);
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
