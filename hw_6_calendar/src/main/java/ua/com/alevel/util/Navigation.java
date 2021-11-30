package ua.com.alevel.util;

public class Navigation {

    public static void helper() {
        System.out.println("\n===================== CALENDAR MENU =======================");
        System.out.println("Action with Dates: ");
        System.out.println("1 - Add new Date");
        System.out.println("2 - Print all Dates");
        System.out.println("3 - Difference between Dates");
        System.out.println("4 - Increase/Decrease Date");
        System.out.println("5 - Sort Dates");
        System.out.println("6 - Clear All Dates");
        System.out.println("0 - Exit");
        System.out.print("\nChoose action you want: ");
    }

    public static void printSortMenu() {
        System.out.println("\n--------------------- SORT DATES -----------------------");
        System.out.println("1 - Sort Dates in Ascending order");
        System.out.println("2 - Sort Dates in Descending order");
        System.out.println("0 - Exit");
        System.out.print("Choose action, what you want: ");
    }

    public static void printIncreaseDecreaseMenu() {
        System.out.println("\n--------------------- CHANGE DATE -----------------------");
        System.out.println("1 - Increase Date");
        System.out.println("2 - Decrease Date");
        System.out.println("0 - Exit");
        System.out.print("Choose action, what you want: ");
    }

    public static void printFormatMenu() {
        System.out.println("1 - dd/mm/yyyy 00:00:00:000");
        System.out.println("2 - m/d/yyyy 00:00:00:000");
        System.out.println("3 - mmm-d-yy 00:00:00:000");
        System.out.println("4 - dd-mmm-yyyy 00:00:00:000");
    }
}
