package com.itryhard;

import java.util.List;

import java.awt.*;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

public class BookingView extends JFrame{
    private final DefaultListModel<String> bookingListModel = new DefaultListModel<>();
    private final JList<String> bookingList = new JList<>(bookingListModel);
    private final JTextField nameField = new JTextField(10);
    private final JTextField tableField = new JTextField(5);
    private final JTextField timeField = new JTextField(5);
    private final JButton addButton = new JButton("Add Booking");
    private final JButton cancelButton = new JButton("Cancel Booking");
    private final JButton removeButton = new JButton("Remove Booking");
    private final JButton arrivedButton = new JButton("Mark as Arrived");

    public BookingView() {
        setTitle("Table Booking System");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Table:"));
        inputPanel.add(tableField);
        inputPanel.add(new JLabel("Time:"));
        inputPanel.add(timeField);
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        add(new JScrollPane(bookingList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(arrivedButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getNameInput() {
        return nameField.getText();
    }

    public String getTableInput() {
        return tableField.getText();
    }

    public String getTimeInput() {
        return timeField.getText();
    }

    public void clearInputs() {
        nameField.setText("");
        tableField.setText("");
        timeField.setText("");
    }

    public void updateBookingList(List<String> bookings) {
        bookingListModel.clear();
        for (String booking : bookings) {
            bookingListModel.addElement(booking);
        }
    }

    public int getSelectedBookingIndex() {
        return bookingList.getSelectedIndex();
    }

    public void addBookingListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void removeBookingListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    public void markAsArrivedListener(ActionListener listener) {
        arrivedButton.addActionListener(listener);
    }
    public void cancelBookingListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }
}
