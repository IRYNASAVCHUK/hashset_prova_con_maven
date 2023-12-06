package com.example.hashset;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.example.logger.MyLogger;

public class MyHashSet {
    private static final Logger logger = MyLogger.getLogger();
    Set<Customer> customers;

    public MyHashSet() {
        logger.entering(getClass().getName(), "MyHashSet");
        this.customers = new HashSet<>();
    }

    public void addCustomer(String name) {
        logger.entering(getClass().getName(), "addCustomer", new Object[] { name });
        long nextId = getNextId();
        Customer e = new Customer(nextId, name);
        customers.add(e);
        logger.fine("Added customer: " + name + " with ID: " + nextId);
        logger.exiting(getClass().getName(), "addCustomer", e);
    }

    private long getNextId() {
        long maxId = 0;
        for (Customer customer : customers) {
            maxId = Math.max(maxId, customer.getId());
        }
        return maxId + 1;
    }

    public void removeCustomer(long id) {
        customers.remove(new Customer(id, ""));
    }

    public boolean containsCustomer(long id) {
        return customers.contains(new Customer(id, ""));
    }

    public int getSize() {
        return customers.size();
    }

    public void clearSet() {
        customers.clear();
    }
}
