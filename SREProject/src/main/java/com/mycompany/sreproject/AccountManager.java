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

public class AccountManager {
    private Map<Integer, String> accounts;
    private AtomicInteger accountIdCounter;
    private static final String FILE_NAME = "accounts.dat";

    public AccountManager() {
        accounts = new HashMap<>();
        accountIdCounter = new AtomicInteger(001); // Starting account ID
        loadAccounts();
    }

    public int openAccount(String accountHolder, String accountType) {
        int accountId = accountIdCounter.getAndIncrement();
        accounts.put(accountId, accountHolder + "," + accountType);
        saveAccounts();
        System.out.println("Account opened for " + accountHolder + " with account type " + accountType);
        return accountId;
    }

    public void closeAccount(int accountId) {
        accounts.remove(accountId);
        saveAccounts();
        System.out.println("Account with ID " + accountId + " closed.");
    }

    private void saveAccounts() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(accounts);
            out.writeObject(accountIdCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAccounts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (Map<Integer, String>) in.readObject();
            accountIdCounter = (AtomicInteger) in.readObject();
        } catch (FileNotFoundException e) {
            // Ignore, file will be created when saving
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
