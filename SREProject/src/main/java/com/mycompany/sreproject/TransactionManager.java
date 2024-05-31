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

public class TransactionManager {
    private Map<Integer, Double> accountBalances;
    private static final String FILE_NAME = "transactions.dat";

    public TransactionManager() {
        accountBalances = new HashMap<>();
        loadBalances();
    }

    public void deposit(int accountId, double amount) {
        accountBalances.put(accountId, accountBalances.getOrDefault(accountId, 0.0) + amount);
        saveBalances();
        System.out.println("Deposited $" + amount + " to account ID " + accountId);
    }

    public void withdraw(int accountId, double amount) {
        if (accountBalances.getOrDefault(accountId, 0.0) >= amount) {
            accountBalances.put(accountId, accountBalances.get(accountId) - amount);
            saveBalances();
            System.out.println("Withdrew $" + amount + " from account ID " + accountId);
        } else {
            System.out.println("Insufficient balance in account ID " + accountId);
        }
    }

    private void saveBalances() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(accountBalances);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBalances() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accountBalances = (Map<Integer, Double>) in.readObject();
        } catch (FileNotFoundException e) {
            // Ignore, file will be created when saving
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


