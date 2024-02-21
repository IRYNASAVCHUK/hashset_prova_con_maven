package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.logger.MyRecordEntering;
import com.example.logger.MyRecordExiting;

import java.util.*;
import java.util.logging.Logger;

public class MyHashSet {
    private static final Logger logger = MyLogger.getLogger();
    private Set<Customer> customers;

    public MyHashSet() {
        MyRecordEntering enter = new MyRecordEntering(null, this, false);
        logger.entering(getClass().getName(), "MyHashSet", enter);
        this.customers = new HashSet<>();
        MyRecordExiting<MyHashSet> result = new MyRecordExiting<>(MyHashSet.class, this, null, this, false);
        logger.exiting(getClass().getName(), "MyHashSet", result);
    }

    public boolean addCustomer(Customer person) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { person }, this, false);
        logger.entering(getClass().getName(), "addCustomer", enter);
        boolean returnValue = customers.add(person);
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person },
                this, false);
        logger.exiting(getClass().getName(), "addCustomer", result);
        return returnValue;
    }

    public boolean removeCustomer(Customer person) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { person }, this, false);
        logger.entering(getClass().getName(), "removeCustomer", enter);
        boolean returnValue = customers.remove(person);
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person },
                this, false);
        logger.exiting(getClass().getName(), "removeCustomer", result);
        return returnValue;
    }

    public boolean containsCustomer(Customer person) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { person }, this, false);
        logger.entering(getClass().getName(), "containsCustomer", enter);
        boolean returnValue = customers.contains(person);
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person },
                this, false);
        logger.exiting(getClass().getName(), "containsCustomer", result);
        return returnValue;
    }

    public int getSize() {
        MyRecordEntering enter = new MyRecordEntering(null, this, false);
        logger.entering(getClass().getName(), "getSize", enter);
        int returnValue = customers.size();
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, returnValue, null, this, false);
        logger.exiting(getClass().getName(), "getSize", result);
        return returnValue;
    }

    public void clearSet() {
        MyRecordEntering enter = new MyRecordEntering(null, this, false);
        logger.entering(getClass().getName(), "clearSet", enter);
        customers.clear();
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, null, this, false);
        logger.exiting(getClass().getName(), "clearSet", result);
    }

    @Override
    public String toString() {
        MyRecordEntering enter = new MyRecordEntering(null, this, false);
        logger.entering(getClass().getName(), "toString", enter);
        String returnValue = "{" + "customers = " + customers + '}';
        MyRecordExiting<String> result = new MyRecordExiting<>(String.class, returnValue, null, this, false);
        logger.exiting(getClass().getName(), "toString", result);
        return returnValue;
    }

    // metodi statici:

    public static int sum(int num1, int num2) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { num1,num2 }, null, true);
        logger.entering(MyHashSet.class.getName(), "sum", enter);
        int returnValue = num1 + num2;
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, returnValue,new Object[] { num1,num2}, null, true);
        logger.exiting(MyHashSet.class.getName(), "sum", result);
        return returnValue;
    }
}