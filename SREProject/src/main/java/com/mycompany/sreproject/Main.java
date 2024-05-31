/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sreproject;

/**
 *
 * @author Admin
 */
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize the GUI
        SwingUtilities.invokeLater(() -> {
            BankManagementGUI gui = new BankManagementGUI();
            gui.createAndShowGUI();
        });
    }
}
