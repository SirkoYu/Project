package com.itryhard_test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.itryhard.Main;

public class MainTest {

    @Test
    void testIsValidName() {
        assertTrue(Main.isValidName("John Doe"));
        assertTrue(Main.isValidName("Doe, John"));
        assertFalse(Main.isValidName("John"));
        assertFalse(Main.isValidName("John123 Doe"));
        assertTrue(Main.isValidName("John,  Doe"));
    }

    @Test
    void testFormatName() {
        assertEquals("John Doe", Main.formatName("John Doe"));
        assertEquals("John Doe", Main.formatName("  John   Doe  "));
    }

    @Test
    void testCapitalize() {
        assertEquals("John", Main.capitalize("john"));
        assertEquals("Doe", Main.capitalize("DOE"));
        assertEquals("Test", Main.capitalize("tEsT"));
        assertEquals("", Main.capitalize(""));
        assertEquals(null, Main.capitalize(null));
    }
}
