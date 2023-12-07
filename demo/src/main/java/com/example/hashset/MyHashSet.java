package com.example.hashset;

import java.util.*;
import java.util.logging.Logger;

import com.example.logger.MyLogger;

public class MyHashSet {
    private static final Logger logger = MyLogger.getLogger();
    Set<Customer> customers;

    public MyHashSet() {
        logger.entering(getClass().getName(), "MyHashSet");
        this.customers = new HashSet<>();
        logger.exiting(getClass().getName(), "MyHashSet", this);
    }

    public boolean addCustomer(long id, String name) {
        logger.entering(getClass().getName(), "addCustomer", new Object[] { id,name });
        Customer e = new Customer(id, name);
        boolean returnValue =customers.add(e);
        logger.exiting(getClass().getName(), "addCustomer", new Object[] { id,name, returnValue });
        return returnValue;
    }
    public boolean removeCustomer(long id) {
        logger.entering(getClass().getName(), "removeCustomer",new Object[] { id });
        boolean returnValue = customers.remove(new Customer(id, ""));
        logger.exiting(getClass().getName(), "removeCustomer", returnValue);
        return returnValue;
    }

    public boolean containsCustomer(long id) {
        logger.entering(getClass().getName(), "containsCustomer",new Object[] { id });
        boolean returnValue =customers.contains(new Customer(id, ""));
        logger.exiting(getClass().getName(), "containsCustomer", returnValue);
        return returnValue;
    }

    public int getSize() {
        logger.entering(getClass().getName(), "getSize");
        int returnValue=customers.size();
        logger.exiting(getClass().getName(), "getSize", returnValue);
        return returnValue;
       
    }

    public void clearSet() {
        logger.entering(getClass().getName(), "clearSet");
        customers.clear();
        logger.exiting(getClass().getName(), "clearSet", customers == null);
    }
}
