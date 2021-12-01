package ua.com.alevel.util;

import org.junit.jupiter.api.*;
import ua.com.alevel.date.CustomDate;
import ua.com.alevel.date.CustomDateList;

import java.io.IOException;

import static ua.com.alevel.date.CustomDateList.addDate;
import static ua.com.alevel.date.CustomDateList.printList;
import static ua.com.alevel.util.StringToDate.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StringToDateTest {

    private CustomDate testDate;
    @Test
    @Order(1)
    public void createCalendarWithDifferentDateFormat() {
        try {
            String[] temp;
            //1. Dates Without Time
            testDate = new CustomDate();
            String input = "/2/";
            ddMmYyyy(input, testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "//";
            mmDdYyyy(input, testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "Jun-22-2021";
            mmmDdYyyy(input, testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "27-Dec-2021";
            ddMmmYyyy(input, testDate);
            addDate(testDate);

            //2. Dates With hh:mm
            testDate = new CustomDate();
            input = "23/11/2021 12:30";
            temp = input.split(" ");
            ddMmYyyy(temp[0], testDate);
            hhMM(temp[1], testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "12/27/2021 14:35";
            temp = input.split(" ");
            mmDdYyyy(temp[0], testDate);
            hhMM(temp[1], testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "Jul-21-2021 :10";
            temp = input.split(" ");
            mmmDdYyyy(temp[0], testDate);
            hhMM(temp[1], testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "8-Mar-2021 17:";
            temp = input.split(" ");
            ddMmmYyyy(temp[0], testDate);
            hhMM(temp[1], testDate);
            addDate(testDate);

            //3. Dates With hh:mm:ss
            testDate = new CustomDate();
            input = "23/11/2021 12:30:42";
            temp = input.split(" ");
            ddMmYyyy(temp[0], testDate);
            hhMMSs(temp[1], testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "12/27/2021 14:35:34";
            temp = input.split(" ");
            mmDdYyyy(temp[0], testDate);
            hhMMSs(temp[1], testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "Jul-21-2021 12:10:10";
            temp = input.split(" ");
            mmmDdYyyy(temp[0], testDate);
            hhMMSs(temp[1], testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "8-Mar-2021 17:30:35";
            temp = input.split(" ");
            ddMmmYyyy(temp[0], testDate);
            hhMMSs(temp[1], testDate);
            addDate(testDate);

            //4. Dates With hh:mm:ss:mmm
            testDate = new CustomDate();
            input = "23/11/2021 12:30:42:321";
            temp = input.split(" ");
            ddMmYyyy(temp[0], testDate);
            hhMMSsMmm(temp[1], testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "12/27/2021 14:35:34:321";
            temp = input.split(" ");
            mmDdYyyy(temp[0], testDate);
            hhMMSsMmm(temp[1], testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "Jul-21-2021 12:10:10:321";
            temp = input.split(" ");
            mmmDdYyyy(temp[0], testDate);
            hhMMSsMmm(temp[1], testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "8-Mar-2021 :::321";
            temp = input.split(" ");
            ddMmmYyyy(temp[0], testDate);
            hhMMSsMmm(temp[1], testDate);
            addDate(testDate);

            //printList();

            Assertions.assertEquals(CustomDateList.dateList.size(), 16);
            CustomDateList.dateList.clear();

            //printList();
        } catch (NumberFormatException | IOException e ) {
            //System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void createIncorrectDateFormat() {
        try {
            testDate = new CustomDate();
            String input = "hkj/hjg/hjk";
            ddMmYyyy(input, testDate);
            addDate(testDate);
        } catch (NumberFormatException | IOException e) {
            //System.out.println("Error: " + e.getMessage());
        }

        try {
            testDate = new CustomDate();
            String input = "30/02/2000";
            ddMmYyyy(input, testDate);
            addDate(testDate);
        } catch (NumberFormatException | IOException e) {
            //System.out.println("Error: " + e.getMessage());
        }

        try {
            testDate = new CustomDate();
            String input = "30/13/2000";
            ddMmYyyy(input, testDate);
            addDate(testDate);
        } catch (NumberFormatException | IOException e) {
            //System.out.println("Error: " + e.getMessage());
        }

        try {
            testDate = new CustomDate();
            String input = "30/13/200000";
            ddMmYyyy(input, testDate);
            addDate(testDate);
        } catch (NumberFormatException | IOException e) {
            //System.out.println("Error: " + e.getMessage());
        }

        //printList();
        Assertions.assertEquals(CustomDateList.dateList.size(), 0);
        CustomDateList.dateList.clear();
    }
}
