/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sreproject;

/**
 *
 * @author Admin
 */
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LoanManager {
    private Map<Integer, Double> loans;
    private AtomicInteger loanIdCounter;
    private static final String FILE_NAME = "loans.dat";

    public LoanManager() {
        loans = new HashMap<>();
        loanIdCounter = new AtomicInteger(2000); // Starting loan ID
        loadLoans();
    }

    public void applyForLoan(int accountId, double loanAmount) {
        int loanId = loanIdCounter.getAndIncrement();
        loans.put(loanId, loanAmount);
        saveLoans();
        System.out.println("Loan application submitted for account ID " + accountId + " for amount $" + loanAmount);
    }

    private void saveLoans() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(loans);
            out.writeObject(loanIdCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadLoans() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            loans = (Map<Integer, Double>) in.readObject();
            loanIdCounter = (AtomicInteger) in.readObject();
        } catch (FileNotFoundException e) {
            // Ignore, file will be created when saving
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
