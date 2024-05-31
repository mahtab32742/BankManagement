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

public class CustomerRelationshipManager {
    private Map<Integer, String> customerProfiles;
    private AtomicInteger customerIdCounter;
    private static final String FILE_NAME = "customers.dat";

    public CustomerRelationshipManager() {
        customerProfiles = new HashMap<>();
        customerIdCounter = new AtomicInteger(3000); // Starting customer ID
        loadCustomerProfiles();
    }

    public void addCustomerProfile(String name, String email) {
        int customerId = customerIdCounter.getAndIncrement();
        customerProfiles.put(customerId, name + "," + email);
        saveCustomerProfiles();
        System.out.println("Customer profile created for " + name + " with email " + email);
    }

    public void updateCustomerProfile(int customerId, String newEmail) {
        String profile = customerProfiles.get(customerId);
        if (profile != null) {
            String[] details = profile.split(",");
            customerProfiles.put(customerId, details[0] + "," + newEmail);
            saveCustomerProfiles();
            System.out.println("Customer profile with ID " + customerId + " updated with new email " + newEmail);
        } else {
            System.out.println("Customer profile with ID " + customerId + " not found.");
        }
    }

    private void saveCustomerProfiles() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(customerProfiles);
            out.writeObject(customerIdCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerProfiles() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            customerProfiles = (Map<Integer, String>) in.readObject();
            customerIdCounter = (AtomicInteger) in.readObject();
        } catch (FileNotFoundException e) {
            // Ignore, file will be created when saving
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
