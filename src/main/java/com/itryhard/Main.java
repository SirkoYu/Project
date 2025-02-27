package com.itryhard;

import java.util.Scanner;

public class Main {
    // Scanner initialisation
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        //Get username
        String username = scanner.nextLine();
        System.out.printf("Hello %s!", username);
    }
}