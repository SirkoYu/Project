package com.itryhard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BookingModel {
    private final String FILE_NAME = "bookings.txt";
    private final List<String> bookings = new ArrayList<>();
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+\\s+[a-zA-Z]+$|^[a-zA-Z]+,\\s*[a-zA-Z]+$");
    private static final Pattern TIME_PATTERN = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");

    public BookingModel() {
        ensureFileExists();
        loadBookings();
    }

    private void ensureFileExists() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    private void loadBookings() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bookings.add(line);
            }
        } catch (IOException e) {
            System.out.println("No existing bookings found.");
        }
    }

    private void saveBookings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String booking : bookings) {
                writer.write(booking);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getBookings() {
        return bookings;
    }

    public boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    public boolean isValidTime(String time) {
        return TIME_PATTERN.matcher(time).matches();
    }

    public boolean isBookingExists(String name, String table, String time) {
        return bookings.stream().anyMatch(b -> b.contains(name) || b.contains("Table: " + table + " at " + time));
    }

    public void addBooking(String name, String table, String time) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid name format! Use: Name Surname or Name, Surname.");
        }
        if (!isValidTime(time)) {
            throw new IllegalArgumentException("Invalid time format! Use HH:MM (24-hour format).");
        }
        if (isBookingExists(name, table, time)) {
            throw new IllegalArgumentException("Booking already exists for this name or table at the same time.");
        }
        String booking = name + " - Table: " + table + " at " + time + " [PENDING]";
        bookings.add(booking);
        saveBookings();
    }

    public void markAsCanceled(int index) {
        if (index >= 0 && index < bookings.size()) {
            bookings.set(index, bookings.get(index).replace("[PENDING]", "[CANCELED]").replace("[ARRIVED]", "[CANCELED]"));
            saveBookings();
        }
    }

    public void removeBooking(int index) {
        if (index >= 0 && index < bookings.size()) {
            bookings.remove(index);
            saveBookings();
        }
    }
    public void markAsArrived(int index) {
        if (index >= 0 && index < bookings.size()) {
            bookings.set(index, bookings.get(index).replace("[PENDING]", "[ARRIVED]"));
            saveBookings();
        }
    }
}
