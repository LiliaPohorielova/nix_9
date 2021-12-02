package ua.com.alevel.date;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static ua.com.alevel.date.CustomDateList.*;
import static ua.com.alevel.dateChange.DecreaseDate.*;
import static ua.com.alevel.dateChange.IncreaseDate.*;
import static ua.com.alevel.dateChange.IncreaseDate.increaseHour;
import static ua.com.alevel.util.StringToDate.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomDateListTest {

    private static final Logger INFO = LoggerFactory.getLogger("info");
    private static final Logger WARN = LoggerFactory.getLogger("info");
    private static final Logger ERROR = LoggerFactory.getLogger("error");

    private CustomDate testDate;

    @Test
    @Order(1)
    public void createCalendarWithDifferentDateFormat() {
        INFO.info("Add new date");
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
            Assertions.assertEquals(CustomDateList.dateList.size(), 16);
            CustomDateList.dateList.clear();
        } catch (NumberFormatException | IOException e) {
            ERROR.error(e.getMessage());
        }
    }

    @Test
    @Order(2)
    public void createIncorrectDateFormat() {
        INFO.info("Add incorrect date");
        try {
            testDate = new CustomDate();
            String input = "hkj/hjg/hjk";
            ddMmYyyy(input, testDate);
            addDate(testDate);
        } catch (NumberFormatException | IOException e) {
            ERROR.error(e.getMessage());
        }

        try {
            testDate = new CustomDate();
            String input = "30/02/2000";
            ddMmYyyy(input, testDate);
            addDate(testDate);
        } catch (NumberFormatException | IOException e) {
            ERROR.error(e.getMessage());
        }

        try {
            testDate = new CustomDate();
            String input = "30/13/2000";
            ddMmYyyy(input, testDate);
            addDate(testDate);
        } catch (NumberFormatException | IOException e) {
            ERROR.error(e.getMessage());
        }

        try {
            testDate = new CustomDate();
            String input = "30/13/200000";
            ddMmYyyy(input, testDate);
            addDate(testDate);
        } catch (NumberFormatException | IOException e) {
            ERROR.error(e.getMessage());
        }
        Assertions.assertEquals(CustomDateList.dateList.size(), 0);
        CustomDateList.dateList.clear();
    }

    @Test
    @Order(3)
    public void sortingDates() {
        INFO.info("Sorting dates");
        try {
            String[] temp;
            //1. Dates Without Time
            testDate = new CustomDate();
            String input = "/2/";
            ddMmYyyy(input, testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "Jun-22-2021";
            mmmDdYyyy(input, testDate);
            addDate(testDate);

            testDate = new CustomDate();
            input = "//";
            mmDdYyyy(input, testDate);
            addDate(testDate);
            sortDateList(true);

            Assertions.assertEquals(CustomDateList.dateList.get(0).getDay(), testDate.getDay());
            Assertions.assertEquals(CustomDateList.dateList.get(0).getMonth(), testDate.getMonth());
            Assertions.assertEquals(CustomDateList.dateList.get(0).getYear(), testDate.getYear());
            CustomDateList.dateList.clear();
        } catch (NumberFormatException | IOException e) {
            ERROR.error(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void IncreaseDecreaseDates() {
        WARN.warn("Increase Decrease Date");
        try {
            testDate = new CustomDate(12, 5, 2003, 5, 0, 0, 0);
            testDate = decreaseYear(1, testDate);
            Assertions.assertEquals(testDate.getYear(), 2002);
            testDate = decreaseDay(1, testDate);
            Assertions.assertEquals(testDate.getDay(), 11);
            testDate = decreaseHour(1, testDate);
            Assertions.assertEquals(testDate.getHour(), 4);

            testDate = increaseYear(1, testDate);
            Assertions.assertEquals(testDate.getYear(), 2003);
            testDate = increaseDay(1, testDate);
            Assertions.assertEquals(testDate.getDay(), 12);
            testDate = increaseHour(1, testDate);
            Assertions.assertEquals(testDate.getHour(), 5);
        } catch (NumberFormatException e) {
            ERROR.error(e.getMessage());
        }
    }
}
