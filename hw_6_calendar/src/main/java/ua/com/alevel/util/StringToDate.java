package ua.com.alevel.util;

import ua.com.alevel.date.CustomDate;
import ua.com.alevel.enums.NameOfMonth;

import java.io.IOException;
import java.util.Scanner;

import static ua.com.alevel.date.CustomDateList.addDate;

public class StringToDate {

    public static void stringToDate(int dateFormat) {
        System.out.println("\n\n================== INPUTTING DATE ==================");
        CustomDate date = new CustomDate();
        Scanner scanner = new Scanner(System.in);
        String inputString;
        String[] arrayOfStrings;
        try {
            switch (dateFormat) {
                //1. Dates Without Time
                case 1:
                    System.out.println("Please enter your Date in format: 21/10/2021");
                    inputString = scanner.nextLine();
                    ddMmYyyy(inputString, date);
                    addDate(date);
                    break;
                case 2:
                    System.out.println("Please enter your Date in format: 10/24/2021");
                    inputString = scanner.nextLine();
                    mmDdYyyy(inputString, date);
                    addDate(date);
                    break;
                case 3:
                    System.out.println("Please enter your Date in format: Jan-24-2021");
                    inputString = scanner.nextLine();
                    mmmDdYyyy(inputString, date);
                    addDate(date);
                    break;
                case 4:
                    System.out.println("Please enter your Date in format: 25-Dec-2021");
                    inputString = scanner.nextLine();
                    ddMmmYyyy(inputString, date);
                    addDate(date);
                    break;

                //2. Dates With hh:mm
                case 5:
                    System.out.println("Please enter your Date in format: 25/05/2021 11:59");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    ddMmYyyy(arrayOfStrings[0], date);
                    hhMM(arrayOfStrings[1], date);
                    addDate(date);
                    break;
                case 6:
                    System.out.println("Please enter your Date in format: 06/17/2021 11:59");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    mmDdYyyy(arrayOfStrings[0], date);
                    hhMM(arrayOfStrings[1], date);
                    addDate(date);
                    break;
                case 7:
                    System.out.println("Please enter your Date in format: Jun-24-2021 11:59");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    mmmDdYyyy(arrayOfStrings[0], date);
                    hhMM(arrayOfStrings[1], date);
                    addDate(date);
                    break;
                case 8:
                    System.out.println("Please enter your Date in format: 8-Mar-2021 11:59");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    ddMmmYyyy(arrayOfStrings[0], date);
                    hhMM(arrayOfStrings[1], date);
                    addDate(date);
                    break;

                //3. Dates With hh:mm:ss
                case 9:
                    System.out.println("Please enter your Date in format: 20/03/2021 10:48:30");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    ddMmYyyy(arrayOfStrings[0], date);
                    hhMMSs(arrayOfStrings[1], date);
                    addDate(date);
                    break;
                case 10:
                    System.out.println("Please enter your Date in format: 03/27/2021 16:40:35");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    mmDdYyyy(arrayOfStrings[0], date);
                    hhMMSs(arrayOfStrings[1], date);
                    addDate(date);
                    break;
                case 11:
                    System.out.println("Please enter your Date in format: Apr-22-2021 13:10:22");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    mmmDdYyyy(arrayOfStrings[0], date);
                    hhMMSs(arrayOfStrings[1], date);
                    addDate(date);
                    break;
                case 12:
                    System.out.println("Please enter your Date in format: 20-Aug-2021 11:42:45");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    ddMmmYyyy(arrayOfStrings[0], date);
                    hhMMSs(arrayOfStrings[1], date);
                    addDate(date);
                    break;

                //4. Dates With hh:mm:ss:mmm
                case 13:
                    System.out.println("Please enter your Date in format: 20/03/2021 10:48:30:011");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    ddMmYyyy(arrayOfStrings[0], date);
                    hhMMSsMmm(arrayOfStrings[1], date);
                    addDate(date);
                    break;
                case 14:
                    System.out.println("Please enter your Date in format: 03/27/2021 16:40:35:731");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    mmDdYyyy(arrayOfStrings[0], date);
                    hhMMSsMmm(arrayOfStrings[1], date);
                    addDate(date);
                    break;
                case 15:
                    System.out.println("Please enter your Date in format: Apr-22-2021 13:10:22:999");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    mmmDdYyyy(arrayOfStrings[0], date);
                    hhMMSsMmm(arrayOfStrings[1], date);
                    addDate(date);
                    break;
                case 16:
                    System.out.println("Please enter your Date in format: 20-Aug-2021 11:42:45:321");
                    inputString = scanner.nextLine();
                    arrayOfStrings = inputString.split(" ");
                    ddMmmYyyy(arrayOfStrings[0], date);
                    hhMMSsMmm(arrayOfStrings[1], date);
                    addDate(date);
                    break;
                default:
                    System.out.println("Incorrect choice!");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void ddMmYyyy(String inputString, CustomDate date) throws NumberFormatException, IOException {
        String[] arrayOfStrings = inputString.split("/", 3);
        if (!arrayOfStrings[0].equals("")) date.setDay(Integer.parseInt(arrayOfStrings[0]));
        if (!arrayOfStrings[1].equals("")) date.setMonth(Integer.parseInt(arrayOfStrings[1]));
        if (!arrayOfStrings[2].equals("")) date.setYear(Integer.parseInt(arrayOfStrings[2]));
    }

    public static void mmDdYyyy(String inputString, CustomDate date) throws NumberFormatException, IOException {
        String[] arrayOfStrings = inputString.split("/", 3);
        if (!arrayOfStrings[1].equals("")) date.setDay(Integer.parseInt(arrayOfStrings[1]));
        if (!arrayOfStrings[0].equals("")) date.setMonth(Integer.parseInt(arrayOfStrings[0]));
        if (!arrayOfStrings[2].equals("")) date.setYear(Integer.parseInt(arrayOfStrings[2]));
    }

    public static void mmmDdYyyy(String inputString, CustomDate date) throws NumberFormatException, IOException {
        String[] arrayOfStrings = inputString.split("-", 3);
        if (!arrayOfStrings[0].equals("")) date.setMonth(NameOfMonth.fromString(arrayOfStrings[0]).getMonthOrder());
        if (!arrayOfStrings[1].equals("")) date.setDay(Integer.parseInt(arrayOfStrings[1]));
        if (!arrayOfStrings[2].equals("")) date.setYear(Integer.parseInt(arrayOfStrings[2]));
    }

    public static void ddMmmYyyy(String inputString, CustomDate date) throws NumberFormatException, IOException {
        String[] arrayOfStrings = inputString.split("-", 3);
        if (!arrayOfStrings[0].equals("")) date.setDay(Integer.parseInt(arrayOfStrings[0]));
        if (!arrayOfStrings[1].equals("")) date.setMonth(NameOfMonth.fromString(arrayOfStrings[1]).getMonthOrder());
        if (!arrayOfStrings[2].equals("")) date.setYear(Integer.parseInt(arrayOfStrings[2]));
    }

    public static void hhMM(String inputString, CustomDate date) throws NumberFormatException, IOException {
        String[] hhMM = inputString.split(":", 2);
        if (!hhMM[0].equals("")) date.setHour(Integer.parseInt(hhMM[0]));
        if (!hhMM[1].equals("")) date.setMinute(Integer.parseInt(hhMM[1]));
    }

    public static void hhMMSs(String inputString, CustomDate date) throws NumberFormatException, IOException {
        String[] hhMMSs = inputString.split(":", 3);
        if (!hhMMSs[0].equals("")) date.setHour(Integer.parseInt(hhMMSs[0]));
        if (!hhMMSs[1].equals("")) date.setMinute(Integer.parseInt(hhMMSs[1]));
        if (!hhMMSs[2].equals("")) date.setSecond(Integer.parseInt(hhMMSs[2]));
    }

    public static void hhMMSsMmm(String inputString, CustomDate date) throws NumberFormatException, IOException {
        String[] hhMMSsMmm = inputString.split(":", 4);
        if (!hhMMSsMmm[0].equals("")) date.setHour(Integer.parseInt(hhMMSsMmm[0]));
        if (!hhMMSsMmm[1].equals("")) date.setMinute(Integer.parseInt(hhMMSsMmm[1]));
        if (!hhMMSsMmm[2].equals("")) date.setSecond(Integer.parseInt(hhMMSsMmm[2]));
        if (!hhMMSsMmm[3].equals("")) date.setMillisecond(Integer.parseInt(hhMMSsMmm[3]));
    }
}
