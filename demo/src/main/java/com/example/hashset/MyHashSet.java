package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.logger.MyRecordExiting;

import java.util.*;
import java.util.logging.Logger;

public class MyHashSet {
    static final Logger logger = MyLogger.getLogger();
    Set<Customer> customers;

    public MyHashSet() {
        logger.entering(getClass().getName(), "MyHashSet", new Object[] { this });
        this.customers = new HashSet<>();
        MyRecordExiting<MyHashSet> result = new MyRecordExiting<>(MyHashSet.class, this, new Object[] { this }, this);
        logger.exiting(getClass().getName(), "MyHashSet", result);
    }

    public boolean addCustomer(Customer person) {
        logger.entering(getClass().getName(), "addCustomer", new Object[] { this, person });
        boolean returnValue = customers.add(person);
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { this, person }, this);
        logger.exiting(getClass().getName(), "addCustomer", result);
        return returnValue;
    }

    public boolean removeCustomer(Customer person) {
        logger.entering(getClass().getName(), "removeCustomer", new Object[] { this, person });
        boolean returnValue = customers.remove(person);
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] {this,  person }, this);
        logger.exiting(getClass().getName(), "removeCustomer", result);
        return returnValue;
    }

    public boolean containsCustomer(Customer person) {
        logger.entering(getClass().getName(), "containsCustomer", new Object[] { this, person });
        boolean returnValue = customers.contains(person);
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { this, person }, this);
        logger.exiting(getClass().getName(), "containsCustomer", result);
        return returnValue;
    }

    public int getSize() {
        logger.entering(getClass().getName(), "getSize", new Object[] { this });
        int returnValue = customers.size();
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, returnValue, new Object[] { this }, this);
        logger.exiting(getClass().getName(), "getSize", result);
        return returnValue;
    }

    public void clearSet() {
        logger.entering(getClass().getName(), "clearSet", new Object[] { this });
        customers.clear();
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, new Object[] { this }, this);
        logger.exiting(getClass().getName(), "clearSet", result);
    }

    @Override
    public String toString() {
        logger.entering(getClass().getName(), "toString", new Object[] { this });
        String returnValue = "{" + "customers = " + customers + '}';
        MyRecordExiting<String> result = new MyRecordExiting<>(String.class, returnValue, new Object[] { this }, this);
        logger.exiting(getClass().getName(), "toString", result);
        return returnValue;
    }

    // metodi statici:

    public static void printAllCustomers(MyHashSet myHashSet) {
        logger.entering(MyHashSet.class.getName(), "printAllCustomers", new Object[] { myHashSet });
        for (Customer customer : myHashSet.customers) {
            System.out.println(customer);
        }
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, new Object[] { myHashSet }, null);
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
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { myHashSet, name }, null);
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
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, count, new Object[] { myHashSet, id }, null);
        logger.exiting(MyHashSet.class.getName(), "countCustomersWithIdGreaterThan", result);
        return count;
    }
}