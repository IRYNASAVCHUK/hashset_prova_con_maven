package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.record.MyRecordEntering;
import com.example.record.MyRecordExiting;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyHashSet {
      private static final Logger logger = MyLogger.getLogger();
      private HashSet<Customer> customers;

      public MyHashSet() {
            logger.logp(Level.INFO, getClass().getName(), "init", "ENTRY",
                        new MyRecordEntering(null, this));
            this.customers = new HashSet<>();
            logger.logp(Level.INFO, getClass().getName(), "init", "RETURN",
                        new MyRecordExiting<>(MyHashSet.class, this, null, this));
      }

      public boolean addCustomer(Customer person) {
            logger.logp(Level.INFO, getClass().getName(), "addCustomer", "ENTRY",
                        new MyRecordEntering(new Object[] { person }, this));
            boolean returnValue = customers.add(person);
            logger.logp(Level.INFO, getClass().getName(), "addCustomer", "RETURN",
                        new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this));
            return returnValue;
      }

      public boolean removeCustomer(Customer person) {
            logger.logp(Level.INFO, getClass().getName(), "removeCustomer", "ENTRY",
                        new MyRecordEntering(new Object[] { person }, this));
            boolean returnValue = customers.remove(person);
            logger.logp(Level.INFO, getClass().getName(), "removeCustomer", "RETURN",
                        new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this));
            return returnValue;
      }

      public boolean containsCustomer(Customer person) {
            logger.logp(Level.INFO, getClass().getName(), "containsCustomer", "ENTRY",
                        new MyRecordEntering(new Object[] { person }, this));
            boolean returnValue = customers.contains(person);
            logger.logp(Level.INFO, getClass().getName(), "containsCustomer", "RETURN",
                        new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this));
            return returnValue;
      }

      public int getSize() {
            logger.logp(Level.INFO, getClass().getName(), "getSize", "ENTRY",
                        new MyRecordEntering(null, this));
            int returnValue = customers.size();
            logger.logp(Level.INFO, getClass().getName(), "getSize", "RETURN",
                        new MyRecordExiting<>(int.class, returnValue, null, this));
            return returnValue;
      }

      public void clearSet() {
            logger.logp(Level.INFO, getClass().getName(), "clearSet", "ENTRY",
                        new MyRecordEntering(null, this));
            customers.clear();
            logger.logp(Level.INFO, getClass().getName(), "clearSet", "RETURN",
                        new MyRecordExiting<>(void.class, null, null, this));
      }

      @Override
      public String toString() {
      logger.logp(Level.INFO, getClass().getName(), "toString", "ENTRY",
      new MyRecordEntering(null, this));
      String returnValue = "{" + "customers = " + customers + '}';
      logger.logp(Level.INFO, getClass().getName(), "toString", "RETURN",
      new MyRecordExiting<>(String.class, returnValue, null, this));
      return returnValue;
      }

      // metodo statico:
      public static int sum(int num1, int num2) {
            logger.logp(Level.INFO, MyHashSet.class.getName(), "sum", "ENTRY",
                        new MyRecordEntering(new Object[] { num1, num2 }, null));
            int returnValue = num1 + num2;
            logger.logp(Level.INFO, MyHashSet.class.getName(), "sum", "RETURN",
                        new MyRecordExiting<>(int.class, returnValue, new Object[] { num1, num2 },
                                    null));
            return returnValue;
      }
}