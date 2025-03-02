package com.itryhard;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookingModelTest {

    private BookingModel bookingModel;
    private static final String TEST_FILE = "test_bookings.txt";

    @BeforeAll
    void setup() {
        // Створюємо порожній тестовий файл перед усіма тестами
        try {
            Files.deleteIfExists(Paths.get(TEST_FILE));
            Files.createFile(Paths.get(TEST_FILE));
        } catch (IOException e) {
            fail("Failed to setup test file");
        }
    }

    @BeforeEach
    void init() {
        // Перед кожним тестом створюємо нову модель, яка працює з тестовим файлом
        bookingModel = new BookingModel() {
            @Override
            protected String getFileName() {
                return TEST_FILE;
            }
        };
    }

    @AfterEach
    void cleanup() {
        // Після кожного тесту чистимо файл
        try {
            Files.write(Paths.get(TEST_FILE), new byte[0]);
        } catch (IOException e) {
            fail("Failed to cleanup test file");
        }
    }

    @AfterAll
    void teardown() {
        // Після всіх тестів видаляємо тестовий файл
        try {
            Files.deleteIfExists(Paths.get(TEST_FILE));
        } catch (IOException e) {
            fail("Failed to delete test file");
        }
    }

    @Test
    void testValidName() {
        assertTrue(bookingModel.isValidName("John Doe"));
        assertTrue(bookingModel.isValidName("Doe, John"));
        assertFalse(bookingModel.isValidName("John123 Doe"));
    }

    @Test
    void testValidTime() {
        assertTrue(bookingModel.isValidTime("12:30"));
        assertFalse(bookingModel.isValidTime("25:00"));
    }

    @Test
    void testAddBooking() {
        bookingModel.addBooking("John Doe", "1", "12:00");
        List<String> bookings = bookingModel.getBookings();
        assertEquals(1, bookings.size());
        assertTrue(bookings.get(0).contains("John Doe - Table: 1 at 12:00 [PENDING]"));
    }

    @Test
    void testDuplicateBookingPrevention() {
        bookingModel.addBooking("John Doe", "1", "12:00");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bookingModel.addBooking("John Doe", "1", "12:00");
        });
        assertTrue(exception.getMessage().contains("Booking already exists"));
    }

    @Test
    void testMarkAsCanceled() {
        bookingModel.addBooking("John Doe", "1", "12:00");
        bookingModel.markAsCanceled(0);
        assertTrue(bookingModel.getBookings().get(0).contains("[CANCELED]"));
    }

    @Test
    void testRemoveBooking() {
        bookingModel.addBooking("John Doe", "1", "12:00");
        bookingModel.removeBooking(0);
        assertTrue(bookingModel.getBookings().isEmpty());
    }

    @Test
    void testMarkAsArrived() {
        bookingModel.addBooking("John Doe", "1", "12:00");
        bookingModel.markAsArrived(0);
        assertTrue(bookingModel.getBookings().get(0).contains("[ARRIVED]"));
    }
}