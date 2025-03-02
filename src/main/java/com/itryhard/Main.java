package com.itryhard;


import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingModel model = new BookingModel();
            BookingView view = new BookingView();
            new BookingController(model, view);
            view.setVisible(true);
        });
    }
}
