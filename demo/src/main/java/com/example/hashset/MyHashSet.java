package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.record.MyRecordEntering;
import com.example.record.MyRecordExiting;

import java.util.*;
import java.util.logging.Logger;

public class MyHashSet {
   private static final Logger logger = MyLogger.getLogger();
   private Set<Customer> customers;

   public MyHashSet() {
      MyRecordEntering enter = new MyRecordEntering(null, this);
      logger.log(MyLogger.logEntering(enter));
      this.customers = new HashSet<>();
      MyRecordExiting<MyHashSet> result = new MyRecordExiting<>(MyHashSet.class, this, null, this);
      logger.log(MyLogger.logExiting(result));
   }

   public boolean addCustomer(Customer person) {
      MyRecordEntering enter = new MyRecordEntering(new Object[] { person }, this);
      logger.log(MyLogger.logEntering(enter));
      boolean returnValue = customers.add(person);
      MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person },
            this);
      logger.log(MyLogger.logExiting(result));
      return returnValue;
   }

   public boolean removeCustomer(Customer person) {
      MyRecordEntering enter = new MyRecordEntering(new Object[] { person }, this);
      logger.log(MyLogger.logEntering(enter));
      boolean returnValue = customers.remove(person);
      MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person },
            this);
      logger.log(MyLogger.logExiting(result));
      return returnValue;
   }

   public boolean containsCustomer(Customer person) {
      MyRecordEntering enter = new MyRecordEntering(new Object[] { person }, this);
      logger.log(MyLogger.logEntering(enter));
      boolean returnValue = customers.contains(person);
      MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person },
            this);
      logger.log(MyLogger.logExiting(result));
      return returnValue;
   }

   public int getSize() {
      MyRecordEntering enter = new MyRecordEntering(null, this);
      logger.log(MyLogger.logEntering(enter));
      int returnValue = customers.size();
      MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, returnValue, null, this);
      logger.log(MyLogger.logExiting(result));
      return returnValue;
   }

   public void clearSet() {
      MyRecordEntering enter = new MyRecordEntering(null, this);
      logger.log(MyLogger.logEntering(enter));
      customers.clear();
      MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, null, this);
      logger.log(MyLogger.logExiting(result));
   }

   @Override
   public String toString() {
      MyRecordEntering enter = new MyRecordEntering(null, this);
      logger.log(MyLogger.logEntering(enter));
      String returnValue = "{" + "customers = " + customers + '}';
      MyRecordExiting<String> result = new MyRecordExiting<>(String.class, returnValue, null, this);
      logger.log(MyLogger.logExiting(result));
      return returnValue;
   }

   // metodi statici:

   public static int sum(int num1, int num2) {
      MyRecordEntering enter = new MyRecordEntering(new Object[] { num1, num2 }, null);
      logger.log(MyLogger.logEntering(enter));
      int returnValue = num1 + num2;
      MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, returnValue, new Object[] { num1, num2 },
            null);
      logger.log(MyLogger.logExiting(result));
      return returnValue;
   }
}