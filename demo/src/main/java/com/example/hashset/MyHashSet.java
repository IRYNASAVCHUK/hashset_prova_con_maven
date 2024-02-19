package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.logger.MyRecord;

import java.util.*;
import java.util.logging.Logger;

public class MyHashSet {
    static final Logger logger = MyLogger.getLogger();
    Set<Customer> customers;

    public MyHashSet() {
        logger.entering(getClass().getName(), "MyHashSet", null);
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
        logger.entering(getClass().getName(), "getSize", null);
        int returnValue = customers.size();
        MyRecord<Integer> result = new MyRecord<>(int.class, returnValue, null);
        logger.exiting(getClass().getName(), "getSize", result);
        return returnValue;
    }

    public void clearSet() {
        logger.entering(getClass().getName(), "clearSet", null);
        customers.clear();
        MyRecord<Void> result = new MyRecord<>(void.class, null, null);
        logger.exiting(getClass().getName(), "clearSet", result);
    }

    @Override
    public String toString() {
        logger.entering(getClass().getName(), "toString", null);
        String returnValue = "{" + "customers = " + customers + '}';
        MyRecord<String> result = new MyRecord<>(String.class, returnValue, null);
        logger.exiting(getClass().getName(), "toString", result);
        return returnValue;
    }

    // metodi statici:

    public static void printAllCustomers(MyHashSet myHashSet) {
        logger.entering(MyHashSet.class.getName(), "printAllCustomers", new Object[] { myHashSet });
        for (Customer customer : myHashSet.customers) {
            System.out.println(customer);
        }
        MyRecord<Void> result = new MyRecord<>(void.class, null, new Object[] { myHashSet });
        logger.exiting(MyHashSet.class.getName(), "printAllCustomers", result);
    }

    public static boolean containsAnyCustomerWithName(MyHashSet myHashSet, String name) {
        logger.entering(MyHashSet.class.getName(), "containsAnyCustomerWithName", new Object[] { myHashSet, name });
        boolean returnValue = false;
        for (Customer customer : myHashSet.customers) {
            if (customer.getName().equals(name)) {
                returnValue = true;
                break;
            }
        }
        MyRecord<Boolean> result = new MyRecord<>(boolean.class, returnValue, new Object[] { myHashSet, name });
        logger.exiting(MyHashSet.class.getName(), "containsAnyCustomerWithName", result);
        return returnValue;
    }

    public static int countCustomersWithIdGreaterThan(MyHashSet myHashSet, int id) {
        logger.entering(MyHashSet.class.getName(), "countCustomersWithIdGreaterThan", new Object[] { myHashSet, id });
        int count = 0;
        for (Customer customer : myHashSet.customers) {
            if (customer.getId() > id) {
                count++;
            }
        }
        MyRecord<Integer> result = new MyRecord<>(int.class, count, new Object[] { myHashSet, id });
        logger.exiting(MyHashSet.class.getName(), "countCustomersWithIdGreaterThan", result);
        return count;
    }
}