package com.example.hashset;

import java.util.*;
import java.util.logging.Logger;

import com.example.logger.MyLogger;
import com.example.logger.MyRecord;

public class MyHashSet {
    private static final Logger logger = MyLogger.getLogger();
    Set<Customer> customers;

    public MyHashSet() {
        logger.entering(getClass().getName(), "MyHashSet");
        this.customers = new HashSet<>();
        MyRecord<MyHashSet> result = new MyRecord<>(MyHashSet.class, this, null);
        logger.exiting(getClass().getName(), "MyHashSet", result);
    }

    public boolean addCustomer(Customer person) {
        logger.entering(getClass().getName(), "addCustomer", new Object[] { person });
        boolean returnValue = customers.add(person);
        MyRecord<Boolean> result = new MyRecord<>(boolean.class, returnValue, new Object[] { person });
        logger.exiting(getClass().getName(), "addCustomer", result);
        return returnValue;
    }

    public boolean removeCustomer(Customer person) {
        logger.entering(getClass().getName(), "removeCustomer", new Object[] { person });
        boolean returnValue = customers.remove(person);
        MyRecord<Boolean> result = new MyRecord<>(boolean.class, returnValue, new Object[] { person });
        logger.exiting(getClass().getName(), "removeCustomer", result);
        return returnValue;
    }

    public boolean containsCustomer(Customer person) {
        logger.entering(getClass().getName(), "containsCustomer", new Object[] { person });
        boolean returnValue = customers.contains(person);
        MyRecord<Boolean> result = new MyRecord<>(boolean.class, returnValue, new Object[] { person });
        logger.exiting(getClass().getName(), "containsCustomer", result);
        return returnValue;
    }

    public int getSize() {
        logger.entering(getClass().getName(), "getSize");
        int returnValue = customers.size();
        MyRecord<Integer> result = new MyRecord<>(int.class, returnValue, null);
        logger.exiting(getClass().getName(), "getSize", result);
        return returnValue;
    }

    public void clearSet() {
        logger.entering(getClass().getName(), "clearSet");
        customers.clear();
        MyRecord<Void> result = new MyRecord<>(void.class, null, null);
        logger.exiting(getClass().getName(), "clearSet", result);
    }

    @Override
    public String toString() {
        logger.entering(getClass().getName(), "toString");
        String returnValue = "{" + "customers = " + customers + '}';
        MyRecord<String> result = new MyRecord<>(String.class, returnValue, null);
        logger.exiting(getClass().getName(), "toString", result);
        return returnValue;
    }
}