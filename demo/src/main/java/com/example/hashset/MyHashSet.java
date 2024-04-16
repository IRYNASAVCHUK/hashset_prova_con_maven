package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.record.MyRecordEntering;
import com.example.record.MyRecordExiting;
import com.example.utils.Constants;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyHashSet {
      private static final Logger logger = MyLogger.getLogger();
      private HashSet<Customer> customers;

      public MyHashSet() {
            logger.logp(Level.INFO, getClass().getName(), "init", Constants.ENTRY,
                        new MyRecordEntering(null, this));
            this.customers = new HashSet<>();
            logger.logp(Level.INFO, getClass().getName(), "init", Constants.RETURN,
                        new MyRecordExiting<>(MyHashSet.class, this, null, this));
      }

      public boolean addCustomer(Customer person) {
            logger.logp(Level.INFO, getClass().getName(), "addCustomer", Constants.ENTRY,
                        new MyRecordEntering(new Object[] { person }, this));
            boolean returnValue = customers.add(person);
            logger.logp(Level.INFO, getClass().getName(), "addCustomer", Constants.RETURN,
                        new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this));
            return returnValue;
      }

      public boolean removeCustomer(Customer person) {
            logger.logp(Level.INFO, getClass().getName(), "removeCustomer", Constants.ENTRY,
                        new MyRecordEntering(new Object[] { person }, this));
            boolean returnValue = customers.remove(person);
            logger.logp(Level.INFO, getClass().getName(), "removeCustomer", Constants.RETURN,
                        new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this));
            return returnValue;
      }

      public boolean containsCustomer(Customer person) {
            logger.logp(Level.INFO, getClass().getName(), "containsCustomer", Constants.ENTRY,
                        new MyRecordEntering(new Object[] { person }, this));
            boolean returnValue = customers.contains(person);
            logger.logp(Level.INFO, getClass().getName(), "containsCustomer", Constants.RETURN,
                        new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this));
            return returnValue;
      }

      public int getSize() {
            logger.logp(Level.INFO, getClass().getName(), "getSize", Constants.ENTRY,
                        new MyRecordEntering(null, this));
            int returnValue = customers.size();
            logger.logp(Level.INFO, getClass().getName(), "getSize", Constants.RETURN,
                        new MyRecordExiting<>(int.class, returnValue, null, this));
            return returnValue;
      }

      public void clearSet() {
            logger.logp(Level.INFO, getClass().getName(), "clearSet", Constants.ENTRY,
                        new MyRecordEntering(null, this));
            customers.clear();
            logger.logp(Level.INFO, getClass().getName(), "clearSet", Constants.RETURN,
                        new MyRecordExiting<>(void.class, null, null, this));
      }

      @Override
      public String toString() {
      logger.logp(Level.INFO, getClass().getName(), "toString", Constants.ENTRY,
      new MyRecordEntering(null, this));
      String returnValue = "{" + "customers = " + customers + '}';
      logger.logp(Level.INFO, getClass().getName(), "toString", Constants.RETURN,
      new MyRecordExiting<>(String.class, returnValue, null, this));
      return returnValue;
      }

      // metodo statico:
      public static int sum(int num1, int num2) {
            logger.logp(Level.INFO, MyHashSet.class.getName(), "sum", Constants.ENTRY,
                        new MyRecordEntering(new Object[] { num1, num2 }, null));
            int returnValue = num1 + num2;
            logger.logp(Level.INFO, MyHashSet.class.getName(), "sum", Constants.RETURN,
                        new MyRecordExiting<>(int.class, returnValue, new Object[] { num1, num2 },
                                    null));
            return returnValue;
      }
}