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
        /*System.out.println("4 - Finding max or min");
        System.out.println("5 - Find average or median");
        System.out.println("6 - Join or intersection");
        System.out.println("7 - Cut");
        System.out.println("8 - Clear");*/
        System.out.println("9 - Print Math Set");
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
}
