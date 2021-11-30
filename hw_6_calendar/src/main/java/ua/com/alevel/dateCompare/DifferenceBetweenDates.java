package ua.com.alevel.dateCompare;

import ua.com.alevel.date.CustomDate;
import ua.com.alevel.enums.MilliDate;

public class DifferenceBetweenDates {

    public static Long dateToMilliseconds(CustomDate date) {
        return (MilliDate.YEAR.getValueInMillisecondOrder() * date.getYear() +
                MilliDate.MONTH.getValueInMillisecondOrder() * date.getMonth() +
                MilliDate.DAY.getValueInMillisecondOrder() * date.getDay() +
                MilliDate.HOUR.getValueInMillisecondOrder() * date.getHour() +
                MilliDate.MINUTE.getValueInMillisecondOrder() * date.getMinute() +
                MilliDate.SECOND.getValueInMillisecondOrder() * date.getSecond() +
                MilliDate.MILLISECOND.getValueInMillisecondOrder() * date.getMillisecond());
    }

    public static CustomDate millisecondsToDate(Long milli) {
        CustomDate date = new CustomDate();
        date.setYear(Integer.parseInt(String.valueOf(milli / MilliDate.YEAR.getValueInMillisecondOrder())));
        milli -= milli / MilliDate.YEAR.getValueInMillisecondOrder() * MilliDate.YEAR.getValueInMillisecondOrder();
        date.setMonth(Integer.parseInt(String.valueOf(milli / MilliDate.MONTH.getValueInMillisecondOrder())));
        milli -= milli / MilliDate.MONTH.getValueInMillisecondOrder() * MilliDate.MONTH.getValueInMillisecondOrder();
        date.setDay(Integer.parseInt(String.valueOf(milli / MilliDate.DAY.getValueInMillisecondOrder())));
        milli -= milli / MilliDate.DAY.getValueInMillisecondOrder() * MilliDate.DAY.getValueInMillisecondOrder();
        date.setHour(Integer.parseInt(String.valueOf(milli / MilliDate.HOUR.getValueInMillisecondOrder())));
        milli -= milli / MilliDate.HOUR.getValueInMillisecondOrder() * MilliDate.HOUR.getValueInMillisecondOrder();
        date.setMinute(Integer.parseInt(String.valueOf(milli / MilliDate.MINUTE.getValueInMillisecondOrder())));
        milli -= milli / MilliDate.MINUTE.getValueInMillisecondOrder() * MilliDate.MINUTE.getValueInMillisecondOrder();
        date.setSecond(Integer.parseInt(String.valueOf(milli / MilliDate.SECOND.getValueInMillisecondOrder())));
        milli -= milli / MilliDate.SECOND.getValueInMillisecondOrder() * MilliDate.SECOND.getValueInMillisecondOrder();
        date.setMillisecond(milli.intValue());
        return date;
    }
}
