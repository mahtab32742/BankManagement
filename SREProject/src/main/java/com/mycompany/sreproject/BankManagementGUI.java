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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankManagementGUI {
    private JFrame frame;
    private AccountManager accountManager;
    private TransactionManager transactionManager;
    private LoanManager loanManager;
    private CustomerRelationshipManager crm;

    public BankManagementGUI() {
        accountManager = new AccountManager();
        transactionManager = new TransactionManager();
        loanManager = new LoanManager();
        crm = new CustomerRelationshipManager();
    }

    public void createAndShowGUI() {
        frame = new JFrame("Bank Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create the panel with buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));

        JButton openAccountButton = new JButton("Open Account");
        JButton closeAccountButton = new JButton("Close Account");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton applyLoanButton = new JButton("Apply for Loan");
        JButton customerProfileButton = new JButton("Manage Customer Profile");

        panel.add(openAccountButton);
        panel.add(closeAccountButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(applyLoanButton);
        panel.add(customerProfileButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // Add action listeners
        openAccountButton.addActionListener(e -> openAccount());
        closeAccountButton.addActionListener(e -> closeAccount());
        depositButton.addActionListener(e -> deposit());
        withdrawButton.addActionListener(e -> withdraw());
        applyLoanButton.addActionListener(e -> applyForLoan());
        customerProfileButton.addActionListener(e -> manageCustomerProfile());

        
        frame.setVisible(true);
    }

    private void openAccount() {
        String name = JOptionPane.showInputDialog(frame, "Enter account holder name:");
        String type = JOptionPane.showInputDialog(frame, "Enter account type (Saving/Checking):");
        int accountId = accountManager.openAccount(name, type);
        JOptionPane.showMessageDialog(frame, "Account opened successfully! Account ID: " + accountId);
    }

    private void closeAccount() {
        int accountId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter account ID to close:"));
        accountManager.closeAccount(accountId);
        JOptionPane.showMessageDialog(frame, "Account with ID " + accountId + " closed successfully!");
    }

    private void deposit() {
        int accountId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter account ID:"));
        double amount = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter amount to deposit:"));
        transactionManager.deposit(accountId, amount);
        JOptionPane.showMessageDialog(frame, "Amount deposited successfully to account ID " + accountId);
    }

    private void withdraw() {
        int accountId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter account ID:"));
        double amount = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter amount to withdraw:"));
        transactionManager.withdraw(accountId, amount);
        JOptionPane.showMessageDialog(frame, "Amount withdrawn successfully from account ID " + accountId);
    }

    private void applyForLoan() {
        int accountId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter account ID:"));
        double amount = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter loan amount:"));
        loanManager.applyForLoan(accountId, amount);
        JOptionPane.showMessageDialog(frame, "Loan application submitted for account ID " + accountId);
    }

    private void manageCustomerProfile() {
        String[] options = {"Add Profile", "Update Profile"};
        int choice = JOptionPane.showOptionDialog(frame, "Choose an option", "Customer Profile Management",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            String name = JOptionPane.showInputDialog(frame, "Enter customer name:");
            String email = JOptionPane.showInputDialog(frame, "Enter customer email:");
            crm.addCustomerProfile(name, email);
            JOptionPane.showMessageDialog(frame, "Customer profile added successfully!");
        } else if (choice == 1) {
            int customerId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter customer ID:"));
            String newEmail = JOptionPane.showInputDialog(frame, "Enter new email:");
            crm.updateCustomerProfile(customerId, newEmail);
            JOptionPane.showMessageDialog(frame, "Customer profile updated successfully!");
        }
    }
}
