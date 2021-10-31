package ua.com.alevel.level2;

import ua.com.alevel.ChooseTask;

import java.io.IOException;
import java.util.Scanner;

public class BinaryTree {

    static final int MAX = 10;
    static Scanner scanner = new Scanner(System.in);

    public static class Node {
        private final long value;
        private Node leftNode;
        private Node rightNode;

        Node(long value) {
            this.value = value;
            leftNode = null;
            rightNode = null;
        }
    }

    public static void run(Scanner scanner) throws IOException {
        Node node;
        int choice = 0;
        while (true) {
            try {
                System.out.println("\n---------------------- Binary Tree ---------------------");
                System.out.println("Please enter a value for the root of the tree:");
                node = new Node(Integer.parseInt(scanner.nextLine()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect value. Please, try again");
            }
        }
        printMenuOfTree(choice, node);
    }

    private static void printMenuOfTree(int choice, Node node) {
        do {
            System.out.println("\nChoose action: ");
            System.out.println("1 - Add node to binary tree");
            System.out.println("2 - Find the maximum depth");
            System.out.println("3 - Print tree");
            System.out.println("0 - Exit to Level 2 menu");
            System.out.print("Task number you want: ");
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (0 <= choice || choice <= 3)
                        break;
                } catch (NumberFormatException a) {
                    System.out.println("Incorrect value. Please, try again.\n");
                    System.out.print("\nTask number you want: ");
                }
            }
            switch (choice) {
                case 1:
                    int value = 0;
                    while (true) {
                        try {
                            System.out.print("\nPlease enter a value of new node (or 0 to exit to the menu):  ");
                            value = Integer.parseInt(scanner.nextLine());
                            if (value == 0) {
                                break;
                            }
                            addNode(node, value);
                        } catch (NumberFormatException e) {
                            System.out.println("Incorrect value. Please, try again.");
                        }
                    }
                    break;
                case 2:
                    System.out.println("\nThe max depth of the binary tree: " + findMaxDepth(node));
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\nYour binary tree");
                    printBinaryTree(node, 0);
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
            }
        } while (choice != 0);
        try {
            new ChooseTask().level2(scanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addNode(Node node, int value) {
        if (value < node.value) {
            if (node.leftNode == null) {
                node.leftNode = new Node(value);
                System.out.println("Add " + value + " to left branch of " + node.value + " node");
            } else {
                addNode(node.leftNode, value);
            }
        } else if (value > node.value) {
            if (node.rightNode == null) {
                node.rightNode = new Node(value);
                System.out.println("Add " + value + " to right branch of " + node.value + " node");
            } else {
                addNode(node.rightNode, value);
            }
        } else {
            System.out.println("This value has already been added to the tree. Try again.");
        }
    }

    public static int findMaxDepth(Node tree) {
        int result;
        if (tree == null) {
            return 0;
        }
        int treeLeft = findMaxDepth(tree.leftNode);
        int treeRight = findMaxDepth(tree.rightNode);
        if (treeLeft > treeRight) {
            result = treeLeft + 1;
        } else {
            result = treeRight + 1;
        }
        return result;
    }

    private static void printBinaryTree(Node root, int count) {
        if (root == null) {
            return;
        }
        count += MAX;
        printBinaryTree(root.rightNode, count);
        System.out.print("\n");

        for (int i = MAX; i < count; i++) {
            System.out.print(" ");
        }
        System.out.print(root.value + "\n");
        printBinaryTree(root.leftNode, count);
    }
}
