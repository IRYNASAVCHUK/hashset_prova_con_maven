package com.example.set;

import com.example.logger.MyLogger;

import java.util.*;
import java.util.function.Predicate;

public class MySet {
      private Set<Customer> customers;

      public MySet() {
            MyLogger.logMethodEntry("init", this, (Object[]) null);
            this.customers = new HashSet<>();
            MyLogger.logMethodExit("init", this, MySet.class, null, (Object[]) null);
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
            MyLogger.logMethodEntry("getSize", this, (Object[]) null);
            int returnValue = customers.size();
            MyLogger.logMethodExit("getSize", this, int.class, returnValue, (Object[]) null);
            return returnValue;
      }

      public void clearSet() {
            MyLogger.logMethodEntry("clearSet", this, (Object[]) null);
            customers.clear();
            MyLogger.logMethodExit("clearSet", this, void.class, null, (Object[]) null);
      }

      @Override
      public String toString() {
            MyLogger.logMethodEntry("toString", this, (Object[]) null);
            String returnValue = "{" + "customers = " + customers + '}';
            MyLogger.logMethodExit("toString", this, String.class, returnValue, (Object[]) null);
            return returnValue;
      }

      // metodo statico:
      public static int sum(int num1, int num2) {
            MyLogger.logStaticMethodEntry(MySet.class.getName(),"sum", new Object[] { num1, num2 });
            int returnValue = num1 + num2;
            MyLogger.logStaticMethodExit(MySet.class.getName(),"sum", int.class, returnValue, new Object[] { num1, num2 });
            return returnValue;
      }

      private <T> boolean performSetOperation(String methodName, Customer person, Predicate<Customer> operation) {
            MyLogger.logMethodEntry(methodName, this, new Object[] { person });
            boolean result = operation.test(person);
            MyLogger.logMethodExit(methodName, this, boolean.class, result, new Object[] { person });
            return result;
      }
}