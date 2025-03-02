package com.itryhard;

import javax.swing.JOptionPane;

public class BookingController {
    public BookingController(BookingModel model, BookingView view) {
        view.updateBookingList(model.getBookings());

        view.addBookingListener(_ -> {
            try {
                String name = view.getNameInput();
                String table = view.getTableInput();
                String time = view.getTimeInput();
                model.addBooking(name, table, time);
                view.updateBookingList(model.getBookings());
                view.clearInputs();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.removeBookingListener(_ -> {
            int index = view.getSelectedBookingIndex();
            if (index >= 0) {
                model.removeBooking(index);
                view.updateBookingList(model.getBookings());
            } else {
                JOptionPane.showMessageDialog(view, "Select a booking to cancel!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        view.markAsArrivedListener(_ -> {
            int index = view.getSelectedBookingIndex();
            if (index >= 0) {
                model.markAsArrived(index);
                view.updateBookingList(model.getBookings());
            } else {
                JOptionPane.showMessageDialog(view, "Select a booking to mark as arrived!", "Selection Error", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
