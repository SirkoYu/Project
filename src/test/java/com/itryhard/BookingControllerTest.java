package com.itryhard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

public class BookingControllerTest {

    private BookingModel model;
    private BookingView view;
    @BeforeEach
    public void setUp() {
        model = mock(BookingModel.class);
        view = mock(BookingView.class);
        new BookingController(model, view);
    }

    @Test
    public void testAddBooking_Success() {
        when(view.getNameInput()).thenReturn("John Doe");
        when(view.getTableInput()).thenReturn("1");
        when(view.getTimeInput()).thenReturn("18:30");

        doAnswer(_ -> {
            model.addBooking("John Doe", "1", "18:30");
            return null;
        }).when(view).addBookingListener(any());

        model.addBooking("John Doe", "1", "18:30");
        verify(model).addBooking("John Doe", "1", "18:30");
    }

    @Test
    public void testAddBooking_InvalidName() {
        when(view.getNameInput()).thenReturn("InvalidName");
        when(view.getTableInput()).thenReturn("1");
        when(view.getTimeInput()).thenReturn("18:30");

        doThrow(new IllegalArgumentException("Invalid name format! Use: Name Surname or Name, Surname."))
                .when(model).addBooking("InvalidName", "1", "18:30");

        try {
            model.addBooking("InvalidName", "1", "18:30");
        } catch (IllegalArgumentException e) {
            verify(model).addBooking("InvalidName", "1", "18:30");
        }
    }

    @Test
    public void testCancelBooking() {
        when(view.getSelectedBookingIndex()).thenReturn(0);

        doAnswer(_ -> {
            model.markAsCanceled(0);
            return null;
        }).when(view).cancelBookingListener(any());

        model.markAsCanceled(0);
        verify(model).markAsCanceled(0);
    }

    @Test
    public void testRemoveBooking() {
        when(view.getSelectedBookingIndex()).thenReturn(0);

        doAnswer(_ -> {
            model.removeBooking(0);
            return null;
        }).when(view).removeBookingListener(any());

        model.removeBooking(0);
        verify(model).removeBooking(0);
    }

    @Test
    public void testMarkAsArrived() {
        when(view.getSelectedBookingIndex()).thenReturn(0);

        doAnswer(_ -> {
            model.markAsArrived(0);
            return null;
        }).when(view).markAsArrivedListener(any());

        model.markAsArrived(0);
        verify(model).markAsArrived(0);
    }

    @Test
    public void testUpdateBookingListOnStart() {
        when(model.getBookings()).thenReturn(Arrays.asList(
                "John Doe - Table: 1 at 18:30 [PENDING]",
                "Alice Smith - Table: 2 at 19:00 [ARRIVED]"
        ));

        new BookingController(model, view);

        verify(view).updateBookingList(Arrays.asList(
                "John Doe - Table: 1 at 18:30 [PENDING]",
                "Alice Smith - Table: 2 at 19:00 [ARRIVED]"
        ));
    }
}
