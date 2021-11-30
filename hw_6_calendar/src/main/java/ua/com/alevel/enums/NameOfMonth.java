package ua.com.alevel.enums;

public enum NameOfMonth {

    JANUARY(1, "JANUARY"),
    FEBRUARY(2, "FEBRUARY"),
    MARCH(3, "MARCH"),
    APRIL(4, "APRIL"),
    MAY(5, "MAY"),
    JUNE(6, "JUNE"),
    JULY(7, "JULY"),
    AUGUST(8, "AUGUST"),
    SEPTEMBER(9, "SEPTEMBER"),
    OCTOBER(10, "OCTOBER"),
    NOVEMBER(11, "NOVEMBER"),
    DECEMBER(12, "DECEMBER");

    private int monthOrder;
    private String monthName;

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

    public static NameOfMonth fromString(String text) {
        for (NameOfMonth b : NameOfMonth.values()) {
            if (b.monthName.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
