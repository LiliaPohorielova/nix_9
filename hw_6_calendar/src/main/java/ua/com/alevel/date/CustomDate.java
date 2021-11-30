package ua.com.alevel.date;

public class CustomDate {

    private int millisecond;
    private int second;
    private int minute;
    private int hour;
    private int day;
    private int month;
    private int year;

    public CustomDate() {}

    public CustomDate(int day, int month, int year, int hour, int minute, int second, int millisecond) {
        this.millisecond = millisecond;
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(int millisecond) {
        this.millisecond = millisecond;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Date: " + day + "/" + month + "/" + year + " " + hour + ":" + minute + ":" + second + ":" + millisecond;
    }
}
