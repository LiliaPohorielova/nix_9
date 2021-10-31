package ua.com.alevel.level1;

import ua.com.alevel.ChooseTask;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChessHorse {

    public static void run(Scanner scanner) throws IOException {
        int BOARD_SIZE = 8;
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }
        String again = "";
        int xStart = 0, yStart = 0;
        int xEnd = 0, yEnd = 0;
        System.out.println("\n---------------------- Chess Horse ---------------------");
        System.out.println("BOARD SIZE: 8x8");
        do {
            while (true) {
                try {
                    System.out.println("Input start position:");
                    System.out.print("X: ");
                    xStart = scanner.nextInt();
                    if (!isCorrectInput(xStart)) continue;
                    System.out.print("Y: ");
                    yStart = scanner.nextInt();
                    if (!isCorrectInput(yStart)) continue;
                    System.out.println("\nInput end position");
                    System.out.print("X: ");
                    xEnd = scanner.nextInt();
                    if (!isCorrectInput(xEnd)) continue;
                    System.out.print("Y: ");
                    yEnd = scanner.nextInt();
                    if (!isCorrectInput(yEnd)) continue;
                } catch (InputMismatchException ex) {
                    System.out.println("Incorrect input\n");
                    scanner.nextLine();
                    continue;
                }
                break;
            }
            int distX = Math.abs(xStart - xEnd);
            int distY = Math.abs(yStart - yEnd);
            if (((distX == 1 && distY == 2)) || ((distX == 2) && (distY == 1))) {
                System.out.println("\nResult\nChess horse can move to this point");
                showBoard(board, xEnd, yEnd);
            } else {
                System.out.println("\nResult\nChess horse can NOT move to this point");
            }
            scanner.nextLine();
            System.out.println("\nDo you want to continue? (Y/N)");
            again = scanner.nextLine();
            System.out.println();

        } while (again.equalsIgnoreCase("Y"));
        new ChooseTask().level1(scanner);
    }

    static boolean isCorrectInput(int i) {
        if (i < 0 || i > 8) {
            System.out.println("Incorrect input\n");
            return false;
        } else {
            return true;
        }
    }

    public static void showBoard(int[][] board, int x, int y) {
        System.out.println("   1 2 3 4 5 6 7 8");
        int j = 0;
        int tmp = board[x][y];
        board[x - 1][y - 1] = 1;
        for (int i = 0; i < 8; i++) {
            System.out.println((i + 1) + "  " + board[i][j++] + " " + board[i][j++] + " " +
                    board[i][j++] + " " + board[i][j++] + " " + board[i][j++] + " " +
                    board[i][j++] + " " + board[i][j++] + " " + board[i][j]);
            j = 0;
        }
        board[x - 1][y - 1] = tmp;
    }
}
