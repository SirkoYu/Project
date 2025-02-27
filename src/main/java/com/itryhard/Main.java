package com.itryhard;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+\s+[a-zA-Z]+$|^[a-zA-Z]+,\s*[a-zA-Z]+$");

    public static void main(String[] args) {
        System.out.println("Enter your name and surname (format: Name Surname or Name, Surname):");
        String input = scanner.nextLine().trim();

        if (isValidName(input)) {
            String formattedName = formatName(input);
            System.out.printf("Hello %s!", formattedName);
        } else {
            System.out.println("Invalid input. Please enter in format: Name Surname or Name, Surname with only alphabetic characters.");
        }
    }

    public static boolean isValidName(String input) {
        return NAME_PATTERN.matcher(input).matches();
    }

    public static String formatName(String input) {
        input = input.replace(",", "").replaceAll("\\s+", " ").trim();
        String[] parts = input.split(" ");
        return capitalize(parts[0]) + " " + capitalize(parts[1]);
    }

    public static String capitalize(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }
}
