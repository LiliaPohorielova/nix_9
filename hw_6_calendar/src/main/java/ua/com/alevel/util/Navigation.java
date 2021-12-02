package ua.com.alevel.util;

public class Navigation {

    public static void helper() {
        System.out.println("\n===================== CALENDAR MENU =======================");
        System.out.println("Action with Dates: ");
        System.out.println("1 - Add new Date");
        System.out.println("2 - Print all Dates");
        System.out.println("3 - Sort Dates");
        System.out.println("4 - Difference between Dates");
        System.out.println("5 - Increase Date");
        System.out.println("6 - Decrease Date");
        System.out.println("7 - Clear All Dates");
        System.out.println("0 - Exit");
        System.out.print("\nChoose action you want: ");
    }

    public static void printIncreaseDecreaseMenu() {
        System.out.println("1 - Years");
        System.out.println("2 - Days");
        System.out.println("3 - Hours");
        System.out.println("4 - Minutes");
        System.out.println("5 - Seconds");
        System.out.println("6 - Milliseconds");
        System.out.println("0 - Exit");
        System.out.print("Choose number unit of measurement, what you want: ");
    }

    public static void printOutputDateFormatMenu() {
        System.out.println("Output Date Format Menu: ");
        System.out.println("1 - dd/mm/yyyy  00:00:00:000");
        System.out.println("2 - mm/dd/yyyy  00:00:00:000");
        System.out.println("3 - mmm-dd-yyyy 00:00:00:000");
        System.out.println("4 - dd-mmm-yyyy 00:00:00:000");
    }

    public static void printInputDateFormatMenu() {
        String heading1 = "Dates Without Time";
        String heading2 = " Dates With HH:MM";
        String heading3 = " Dates With HH:MM:SS";
        String heading4 = " Dates With HH:MM:SS:MMM";
        String divider = "-------------------------------------------------------------------------------------------------------------------------";

        String format1 = "1 - dd/mm/yyyy ";
        String format2 = "2 - mm/dd/yyyy ";
        String format3 = "3 - mmm-dd-yyyy";
        String format4 = "4 - dd-mmm-yyyy";

        String format5 = " 5 - dd/mm/yyyy  00:00";
        String format6 = " 6 - mm/dd/yyyy  00:00";
        String format7 = " 7 - mmm-dd-yyyy 00:00";
        String format8 = " 8 - dd-mmm-yyyy 00:00";

        String format9 = " 9  - dd/mm/yyyy  00:00:00";
        String format10 = " 10 - mm/dd/yyyy  00:00:00";
        String format11 = " 11 - mmm-dd-yyyy 00:00:00";
        String format12 = " 12 - dd-mmm-yyyy 00:00:00";

        String format13 = " 13 - dd/mm/yyyy  00:00:00:000";
        String format14 = " 14 - mm/dd/yyyy  00:00:00:000";
        String format15 = " 15 - mmm-dd-yyyy 00:00:00:000";
        String format16 = " 16 - dd-mmm-yyyy 00:00:00:000";

        System.out.println(divider);
        System.out.printf("%-21s %1s %-25s %1s %-28s %1s %-35s %1s %n", heading1, "|", heading2, "|", heading3, "|", heading4, "|");
        System.out.println(divider);

        System.out.printf("%-21s %1s %-25s %1s %-28s %1s %-35s %1s %n", format1, "|", format5, "|", format9, "|", format13, "|");
        System.out.printf("%-21s %1s %-25s %1s %-28s %1s %-35s %1s %n", format2, "|", format6, "|", format10, "|", format14, "|");
        System.out.printf("%-21s %1s %-25s %1s %-28s %1s %-35s %1s %n", format3, "|", format7, "|", format11, "|", format15, "|");
        System.out.printf("%-21s %1s %-25s %1s %-28s %1s %-35s %1s %n", format4, "|", format8, "|", format12, "|", format16, "|");

        System.out.println(divider);
        System.out.println("");
    }
}
