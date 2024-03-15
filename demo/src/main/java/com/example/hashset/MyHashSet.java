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
      if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
         this.customers = new HashSet<>();
         logger.log(MyLogger.logExiting(new MyRecordExiting<>(MyHashSet.class, this, null, this)));
      }
      this.customers = new HashSet<>();
   }

   public boolean addCustomer(Customer person) {
      if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { person }, this)));
         boolean returnValue = customers.add(person);
         logger.log(
               MyLogger.logExiting(new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this)));
         return returnValue;
      }
      return customers.add(person);
   }

   public boolean removeCustomer(Customer person) {
      if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { person }, this)));
         boolean returnValue = customers.remove(person);
         logger.log(
               MyLogger.logExiting(new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this)));
         return returnValue;
      }
      return customers.remove(person);
   }

   public boolean containsCustomer(Customer person) {
      if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { person }, this)));
         boolean returnValue = customers.contains(person);
         logger.log(
               MyLogger.logExiting(new MyRecordExiting<>(boolean.class, returnValue, new Object[] { person }, this)));
         return returnValue;
      }
      return customers.contains(person);
   }

   public int getSize() {
      if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
         int returnValue = customers.size();
         logger.log(MyLogger.logExiting(new MyRecordExiting<>(int.class, returnValue, null, this)));
         return returnValue;
      }
      return customers.size();
   }

   public void clearSet() {
      if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
         customers.clear();
         logger.log(MyLogger.logExiting(new MyRecordExiting<>(void.class, null, null, this)));
      }
      customers.clear();
   }

   @Override
   public String toString() {
      if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
         logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
         String returnValue = "{" + "customers = " + customers + '}';
         logger.log(MyLogger.logExiting(new MyRecordExiting<>(String.class, returnValue, null, this)));
         return returnValue;
      }
      return "{" + "customers = " + customers + '}';
   }

   // metodi statici:

   public static int sum(int num1, int num2) {
      logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { num1, num2 }, null)));
      int returnValue = num1 + num2;
      logger.log(MyLogger.logExiting(new MyRecordExiting<>(int.class, returnValue, new Object[] { num1, num2 },
            null)));
      return returnValue;
   }

   private String getCallerMethodName(StackTraceElement[] stackTrace) {
      // Analizza gli elementi dello stack trace per ottenere il chiamante diretto
      String callerMethodName = Arrays.stream(stackTrace)
            // Ignora il primo elemento dello stack trace (quello relativo al metodo
            // getSize() stesso)
            .skip(1)
            // Trova il primo elemento non relativo alla classe corrente
            .filter(element -> !element.getClassName().equals(getClass().getName()))
            // Ottieni il nome del metodo chiamante
            .map(StackTraceElement::getMethodName)
            // Trova il primo nome di metodo non standard (cioè, che non inizia con
            // 'lambda$' o '$$')
            .filter(name -> !name.startsWith("lambda$") && !name.contains("$$"))
            .findFirst()
            .orElse("Unknown");
      return callerMethodName;
   }
}