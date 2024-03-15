package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.record.MyRecordEntering;
import com.example.record.MyRecordExiting;
import com.example.utils.CallerUtil;

import java.util.*;
import java.util.logging.Logger;

public class MyHashSet {
   private static final Logger logger = MyLogger.getLogger();
   private Set<Customer> customers;

   public MyHashSet() {
      if (CallerUtil.isCalledFromMain()) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
         this.customers = new HashSet<>();
         logger.log(MyLogger.logExiting(new MyRecordExiting<>(MyHashSet.class, this, null, this)));
      }
      this.customers = new HashSet<>();
   }

   public boolean addCustomer(Customer person) {
      if (CallerUtil.isCalledFromMain()) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { person }, this)));
         boolean returnValue = customers.add(person);
         logger.log(
               MyLogger.logExiting(new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this)));
         return returnValue;
      }
      return customers.add(person);
   }

   public boolean removeCustomer(Customer person) {
      if (CallerUtil.isCalledFromMain()) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { person }, this)));
         boolean returnValue = customers.remove(person);
         logger.log(
               MyLogger.logExiting(new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this)));
         return returnValue;
      }
      return customers.remove(person);
   }

   public boolean containsCustomer(Customer person) {
      if (CallerUtil.isCalledFromMain()) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { person }, this)));
         boolean returnValue = customers.contains(person);
         logger.log(
               MyLogger.logExiting(new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this)));
         return returnValue;
      }
      return customers.contains(person);
   }

   public int getSize() {
      if (CallerUtil.isCalledFromMain()) {
         System.out.println("*****");
         logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
         int returnValue = customers.size();
         logger.log(MyLogger.logExiting(new MyRecordExiting<>(int.class, returnValue, null, this)));
         return returnValue;
      }
      return customers.size();
   }

   public void clearSet() {
      if (CallerUtil.isCalledFromMain()) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
         customers.clear();
         logger.log(MyLogger.logExiting(new MyRecordExiting<>(void.class, null, null, this)));
      }
      customers.clear();
   }

   @Override
   public String toString() {
      if (CallerUtil.isCalledFromMain()) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
         String returnValue = "{" + "customers = " + customers + '}';
         logger.log(MyLogger.logExiting(new MyRecordExiting<>(String.class, returnValue, null, this)));
         return returnValue;
      }
      return "{" + "customers = " + customers + '}';
   }

   // metodi statici:

   public static int sum(int num1, int num2) {
      if (CallerUtil.isCalledFromMain()) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { num1, num2 }, null)));
         int returnValue = num1 + num2;
         logger.log(MyLogger.logExiting(new MyRecordExiting<>(int.class, returnValue, new Object[] { num1, num2 },
               null)));
         return returnValue;
      }
      return num1 + num2;
   }
}