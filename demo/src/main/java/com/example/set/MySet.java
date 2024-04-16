package com.example.set;

import com.example.logger.MyLogger;
import com.example.record.MyRecordEntering;
import com.example.record.MyRecordExiting;
import com.example.utils.Constants;

import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySet {
      private static final Logger logger = MyLogger.getLogger();
      private Set<Customer> customers;

      public MySet() {
            logMethodEntry("init", (Object[]) null);
            this.customers = new HashSet<>();
            logMethodExit("init", MySet.class, this, (Object[]) null);
      }

      public boolean addCustomer(Customer person) {
            return performSetOperation("addCustomer", person, c -> customers.add(c));
      }

      public boolean removeCustomer(Customer person) {
            return performSetOperation("removeCustomer", person, c -> customers.remove(c));
      }

      public boolean containsCustomer(Customer person) {
            return performSetOperation("containsCustomer", person, c -> customers.contains(c));
      }

      public int getSize() {
            logMethodEntry("getSize", (Object[]) null);
            int returnValue = customers.size();
            logMethodExit("getSize", int.class, returnValue, (Object[]) null);
            return returnValue;
      }

      public void clearSet() {
            logMethodEntry("clearSet", (Object[]) null);
            customers.clear();
            logMethodExit("clearSet", void.class, null, (Object[]) null);
      }

      @Override
      public String toString() {
            logMethodEntry("toString", (Object[]) null);
            String returnValue = "{" + "customers = " + customers + '}';
            logMethodExit("toString", String.class, returnValue, (Object[]) null);
            return returnValue;
      }

      // metodo statico:
      public static int sum(int num1, int num2) {
            logStaticMethodEntry("sum", new Object[] { num1, num2 });
            int returnValue = num1 + num2;
            logStaticMethodExit("sum", int.class, returnValue, new Object[] { num1, num2 });
            return returnValue;
      }

      private void logMethodEntry(String methodName, Object... args) {
            logger.logp(Level.INFO, getClass().getName(), methodName, Constants.ENTRY,
                        new MyRecordEntering(args, this));
      }

      private static void logStaticMethodEntry(String methodName, Object... args) {
            logger.logp(Level.INFO, MySet.class.getName(), methodName, Constants.ENTRY,
                        new MyRecordEntering(args, null));
      }

      private <T> void logMethodExit(String methodName, Class<T> returnType, T result, Object... args) {
            logger.logp(Level.INFO, getClass().getName(), methodName, Constants.RETURN,
                        new MyRecordExiting<>(returnType, result, args, this));
      }

      private static <T> void logStaticMethodExit(String methodName, Class<T> returnType, T result, Object... args) {
            logger.logp(Level.INFO, MySet.class.getName(), methodName, Constants.RETURN,
                        new MyRecordExiting<>(returnType, result, args, null));
      }

      private <T> boolean performSetOperation(String methodName, Customer person, Predicate<Customer> operation) {
            logMethodEntry(methodName, new Object[] { person });
            boolean result = operation.test(person);
            logMethodExit(methodName, boolean.class, result, new Object[] { person });
            return result;
      }
}