package ua.com.alevel.enums;

import java.io.IOException;

public enum NameOfMonth {

    JANUARY(1, "JAN"),
    FEBRUARY(2, "FEB"),
    MARCH(3, "MAR"),
    APRIL(4, "APR"),
    MAY(5, "MAY"),
    JUNE(6, "JUN"),
    JULY(7, "JUL"),
    AUGUST(8, "AUG"),
    SEPTEMBER(9, "SEP"),
    OCTOBER(10, "OCT"),
    NOVEMBER(11, "NOV"),
    DECEMBER(12, "DEC");

    private int monthOrder;
    private String monthName;
    private int monthDays;

    NameOfMonth(int monthOrder) {
        this.monthOrder = monthOrder;
    }

    NameOfMonth(int monthOrder, String monthName) {
        this.monthOrder = monthOrder;
        this.monthName = monthName;
    }

    public int getMonthOrder() {
        return this.monthOrder;
    }

    public String getMonthName() {
        return this.monthName;
    }

    public static NameOfMonth fromString(String text) throws IOException {
        for (NameOfMonth b : NameOfMonth.values()) {
            if (b.monthName.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IOException("Not found such mounth");
    }

    public int getMonthDays() {
        return monthDays;
    }

    public void setMonthDays(int monthDays) {
        this.monthDays = monthDays;
    }
}
