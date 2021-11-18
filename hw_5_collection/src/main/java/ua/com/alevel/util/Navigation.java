package ua.com.alevel.util;

public class Navigation {


    public static void helper() {
        System.out.println("\n===================== MAIN MENU =======================");
        System.out.println("Choose type of Math Set: ");
        System.out.println("1 - Math Set With Default Capacity");
        System.out.println("2 - Math Set With Custom Capacity");
        System.out.println("3 - Math Set With Array of Numbers");
        System.out.println("4 - Math Set With Arrays of Numbers");
        System.out.println("5 - Math Set With Math Set");
        System.out.println("6 - Math Set With Math Sets");
        System.out.println("0 - Exit");
        System.out.print("\nChoose action you want: ");
    }

    public static void menuOfMathSet() {
        System.out.println("Action with the Math Set: ");
        System.out.println("1 - Create");
        System.out.println("2 - Add elements");
        System.out.println("3 - Sorting");
        System.out.println("4 - Get something");
        System.out.println("5 - Join or intersection");
        System.out.println("6 - Cut");
        System.out.println("7 - Clear");
        System.out.println("8 - Print Math Set");
        System.out.println("0 - Exit");
        System.out.print("\nChoose action you want: ");
    }

    public static void printSortMenu() {
        System.out.println("\n--------------------- SORT ELEMENTS  ---------------------");
        System.out.println("1 - Sort Ascending");
        System.out.println("2 - Sort Ascending Part of Math Set");
        System.out.println("3 - Sort Ascending One Number Of Math Set");
        System.out.println("4 - Sort Descending");
        System.out.println("5 - Sort Descending Part of Math Set");
        System.out.println("6 - Sort Descending One Number Of Math Set");
        System.out.println("0 - Exit");
        System.out.print("Choose action, what you want: ");
    }

    public static void printAddMenu() {
        System.out.println("\n--------------------- ADD ELEMENTS  ---------------------");
        System.out.println("1 - Add element");
        System.out.println("2 - Add elements");
        System.out.println("0 - Exit");
        System.out.print("Choose action, what you want: ");
    }

    public static void printGetMenu() {
        System.out.println("\n--------------------- GET SOMETHING ----------------------");
        System.out.println("1 - Max element");
        System.out.println("2 - Min element");
        System.out.println("3 - Average of array");
        System.out.println("4 - Median of array");
        System.out.println("5 - Get element by index");
        System.out.println("0 - Exit");
        System.out.print("Choose action, what you want: ");
    }

    public static void printJoinOrInterMenu() {
        System.out.println("\n--------------------- ADD ELEMENTS  ---------------------");
        System.out.println("1 - Join random math set to current math set");
        System.out.println("2 - Intersection current math set with random math set");
        System.out.println("0 - Exit");
        System.out.print("Choose action, what you want: ");
    }
}
