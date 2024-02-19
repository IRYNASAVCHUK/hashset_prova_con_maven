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
        MyRecordEntering enter = new MyRecordEntering(null, this);
        logger.entering(getClass().getName(), "MyHashSet", enter);
        this.customers = new HashSet<>();
        MyRecordExiting<MyHashSet> result = new MyRecordExiting<>(MyHashSet.class, this, null, this);
        logger.exiting(getClass().getName(), "MyHashSet", result);
    }

    public boolean addCustomer(Customer person) {
        MyRecordEntering enter = new MyRecordEntering( new Object[] { person }, this);
        logger.entering(getClass().getName(), "addCustomer",enter);
        boolean returnValue = customers.add(person);
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this);
        logger.exiting(getClass().getName(), "addCustomer", result);
        return returnValue;
    }

    public boolean removeCustomer(Customer person) {
        MyRecordEntering enter = new MyRecordEntering( new Object[] { person }, this);
        logger.entering(getClass().getName(), "removeCustomer",enter);
        boolean returnValue = customers.remove(person);
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] {person }, this);
        logger.exiting(getClass().getName(), "removeCustomer", result);
        return returnValue;
    }

    public boolean containsCustomer(Customer person) {
        MyRecordEntering enter = new MyRecordEntering( new Object[] { person }, this);
        logger.entering(getClass().getName(), "containsCustomer", enter);
        boolean returnValue = customers.contains(person);
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this);
        logger.exiting(getClass().getName(), "containsCustomer", result);
        return returnValue;
    }

    public int getSize() {
        MyRecordEntering enter = new MyRecordEntering(null, this);
        logger.entering(getClass().getName(), "getSize", enter);
        int returnValue = customers.size();
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, returnValue, null, this);
        logger.exiting(getClass().getName(), "getSize", result);
        return returnValue;
    }

    public void clearSet() {
        MyRecordEntering enter = new MyRecordEntering(null, this);
        logger.entering(getClass().getName(), "clearSet", enter);
        customers.clear();
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, null, this);
        logger.exiting(getClass().getName(), "clearSet", result);
    }

    @Override
    public String toString() {
        MyRecordEntering enter = new MyRecordEntering(null, this);
        logger.entering(getClass().getName(), "toString", enter);
        String returnValue = "{" + "customers = " + customers + '}';
        MyRecordExiting<String> result = new MyRecordExiting<>(String.class, returnValue, null, this);
        logger.exiting(getClass().getName(), "toString", result);
        return returnValue;
    }

    // metodi statici:

    public static void printAllCustomers(MyHashSet myHashSet) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { myHashSet }, null);
        logger.entering(MyHashSet.class.getName(), "printAllCustomers",enter );
        for (Customer customer : myHashSet.customers) {
            System.out.println(customer);
        }
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, new Object[] { myHashSet }, null);
        logger.exiting(MyHashSet.class.getName(), "printAllCustomers", result);
    }

    public static boolean containsAnyCustomerWithName(MyHashSet myHashSet, String name) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { myHashSet, name }, null);
        logger.entering(MyHashSet.class.getName(), "containsAnyCustomerWithName", enter);
        boolean returnValue = false;
        for (Customer customer : myHashSet.customers) {
            if (customer.getName().equals(name)) {
                returnValue = true;
                break;
            }
        }
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { myHashSet, name }, null);
        logger.exiting(MyHashSet.class.getName(), "containsAnyCustomerWithName", result);
        return returnValue;
    }

    public static int countCustomersWithIdGreaterThan(MyHashSet myHashSet, int id) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { myHashSet, id }, null);
        logger.entering(MyHashSet.class.getName(), "countCustomersWithIdGreaterThan", enter);
        int count = 0;
        for (Customer customer : myHashSet.customers) {
            if (customer.getId() > id) {
                count++;
            }
        }
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, count, new Object[] { myHashSet, id }, null);
        logger.exiting(MyHashSet.class.getName(), "countCustomersWithIdGreaterThan", result);
        return count;
    }
}