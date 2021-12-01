package ua.com.alevel.dateValid;

import ua.com.alevel.date.CustomDate;

public class FormatVerify {

    public static boolean isLeapYear(long year) {
        return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
    }

    public static boolean isValidDate(CustomDate customDate) throws NumberFormatException {
        if (customDate.getYear() < 0 || customDate.getYear() > 9999)
            throw new NumberFormatException("Incorrect date!");
        if (customDate.getHour() < 0 || customDate.getHour() > 24)
            throw new NumberFormatException("Incorrect date!");
        if (customDate.getMinute() < 0 || customDate.getMinute() > 60)
            throw new NumberFormatException("Incorrect date!");
        if (customDate.getSecond() < 0 || customDate.getSecond() > 60)
            throw new NumberFormatException("Incorrect date!");
        if (customDate.getMillisecond() < 0 || customDate.getMillisecond() > 999)
            throw new NumberFormatException("Incorrect date!");
        if (customDate.getMonth() < 1 || customDate.getMonth() > 12)
            throw new NumberFormatException("Incorrect date!");

        switch (customDate.getMonth()) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (customDate.getDay() < 1 || customDate.getDay() > 31)
                    throw new NumberFormatException("Incorrect date!");
            case 4:
            case 6:
            case 9:
            case 11:
                if (customDate.getDay() < 1 || customDate.getDay() > 30)
                    throw new NumberFormatException("Incorrect date!");
            case 2:
                if (isLeapYear(customDate.getYear())) {
                    if (customDate.getDay() < 1 || customDate.getDay() > 29)
                        throw new NumberFormatException("Incorrect date!");
                } else {
                    if (customDate.getDay() < 1 || customDate.getDay() > 28)
                        throw new NumberFormatException("Incorrect date!");
                }
            default:
                return true;
        }
    }
}
