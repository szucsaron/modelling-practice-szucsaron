package com.codecool.main;

import java.util.Scanner;

public class UI {
    private Scanner scanner = new Scanner(System.in);

    public void UserInterface() {
    }

    public String getInput(String message) {
        print(message);
        String input = scanner.nextLine();
        return input;
    }

    public int getIntInput(String message) {
        while (true) {
            try {
                return Integer.parseInt(getInput(message));
            } catch (NumberFormatException e) {
                print("Invalid input! Please, try again.");
            }
        }
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void print(int message) {
        System.out.println(message);
    }

    public void printTable(String[][] table) {
        String spacing = "";
        for (int i = 0; i < table[0].length; i++) {
            spacing += "l";
        }
        printTable(table, spacing, 10, "| ");
    }

    public void printTable(String[][] table, String justifyCols) {
        printTable(table, justifyCols, 3, "| ");
    }

    public void printTable(String[][] table, String justifyCols, int extraSpacingLength) {
        printTable(table, justifyCols, extraSpacingLength, "| ");
    }

    public void printTable(String[][] table, String justifyCols, int extraSpacingLength, String border) {
        int rowNum = table.length;
        int colNum = table[0].length;
        int spacingLength = 0;
        String spacingChar = " ";
        int[] colMaxLength = new int[rowNum];
        for (int col = 0; col < colNum; col++) {
            for (int row = 0; row < rowNum; row++) {
                try {
                    int length = table[row][col].length();
                    if (colMaxLength[col] < length) {
                        colMaxLength[col] = length;
                    }
                    if (table[row][col] == null) {
                        break;
                    }
                } catch (NullPointerException e) {
                    rowNum = row;
                    break;
                }
            }
        }
        for (int row = 0; row < rowNum; row++) {
            String rowText = "";
            for (int col = 0; col < colNum; col++) {
                if (table[row][col] == null) {
                    break;
                }
                rowText += border;
                spacingLength = colMaxLength[col] - table[row][col].length() + extraSpacingLength;
                String spacing = new String(new char[spacingLength]).replace("\0", spacingChar);
                switch (justifyCols.charAt(col)) {
                    case 'l':
                        rowText += table[row][col] + spacing;
                        break;
                    case 'r':
                        rowText += spacing + table[row][col];
                        break;
                    default:
                        rowText += table[row][col] + spacing;
                        break;
                }


            }
            rowText += border;
            System.out.println(rowText);
        }
    }

    public void clear() {
        for (int i = 0; i < 50; i++) {
            print("");
        }
    }
}
