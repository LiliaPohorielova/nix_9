package ua.com.alevel.level3;

import ua.com.alevel.ChooseTask;

import java.io.IOException;
import java.util.Scanner;

public class GameOfLife {

    public static final int M = 6, N = 6;
    private static int[][] playField = new int[M][N];
    public static final int[][] TOAD =
            {{0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 0},
                    {0, 1, 1, 1, 0, 0},
                    {0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0}};

    public static void run(Scanner scanner, boolean template) throws IOException {
        String again = "";
        if (template)
            playField = TOAD;
        else
            playField = random(playField);
        do {
            playField = nextState(playField, M, N);
            showGame(playField, true);
            System.out.println("\nDo you want to continue? (Y/N)");
            again = scanner.nextLine();
            System.out.println();
        } while (again.equalsIgnoreCase("Y"));
        new ChooseTask().level3(scanner);
    }

    static int[][] nextState(int present[][], int M, int N) {
        int[][] next = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int countOfAlive = countNeighbors(i, j);
                if ((present[i][j] == 1) && (countOfAlive < 2)) {
                    next[i][j] = 0;
                } else if ((present[i][j] == 1) && (countOfAlive > 3)) {
                    next[i][j] = 0;
                } else if ((present[i][j] == 0) && ((countOfAlive == 3))) {
                    next[i][j] = 1;
                } else {
                    next[i][j] = present[i][j];
                }
            }
        }
        return next;
    }

    private static int countNeighbors(int i, int j) {
        int count = 0;
        for (int i1 = i - 1; i1 <= i + 1; i1++) {
            for (int j1 = j - 1; j1 <= j + 1; j1++) {
                if (i1 == i && j1 == j) continue;
                if (playField[check(i1)][check(j1)] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int check(int coordinate) {
        int result = coordinate;
        if (coordinate < 0) {
            result = M + coordinate;
        } else if (coordinate >= N) {
            result = coordinate - M;
        }
        return result;
    }

    public static int[][] random(int[][] grid) {
        int randomLive;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                randomLive = (int) (Math.random() * 2);
                grid[i][j] = randomLive;
            }
        }
        System.out.println("Present State");
        showGame(grid, false);
        System.out.println();
        return grid;
    }

    public static void showGame(int[][] board, boolean print) {
        if (print)
            System.out.println("Next State");
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0)
                    System.out.print(".   ");
                else
                    System.out.print("A   ");
            }
            System.out.println();
        }
    }
}
