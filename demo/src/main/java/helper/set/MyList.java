package helper.set;

import java.util.*;
import java.util.function.Predicate;

import core.logger.MyLogger;

public class MyList {
      private List<Customer> customers;

      public MyList() {
            MyLogger.logMethodEntry("init", this, (Object[]) null, new Class<?>[0]);
            this.customers = new ArrayList<>();
            MyLogger.logMethodExit("init", this, void.class, null, (Object[]) null, new Class<?>[0]);
      }

      public Boolean addCustomer(Customer person) {
            return performSetOperation("addCustomer", person, c -> customers.add(c));
      }

      public Boolean removeCustomer(Customer person) {
            return performSetOperation("removeCustomer", person, c -> customers.remove(c));
      }

      public Boolean containsCustomer(Customer person) {
            return performSetOperation("containsCustomer", person, c -> customers.contains(c));
      }

      public int getSize() {
            MyLogger.logMethodEntry("getSize", this, (Object[]) null, new Class<?>[0]);
            var returnValue = customers.size();
            MyLogger.logMethodExit("getSize", this, int.class, returnValue, (Object[]) null, new Class<?>[0]);
            return returnValue;
      }

      public void clearSet() {
            MyLogger.logMethodEntry("clearSet", this, (Object[]) null, new Class<?>[0]);
            customers.clear();
            MyLogger.logMethodExit("clearSet", this, void.class, null, (Object[]) null, new Class<?>[0]);
      }

      @Override
      public int hashCode() {
          MyLogger.logMethodEntry("hashCode", this, (Object[]) null, new Class<?>[0]);
          var result =  customers.hashCode();
          MyLogger.logMethodExit("hashCode", this, int.class, result, (Object[]) null, new Class<?>[0]);
          return result;
      }
      @Override
      public String toString() {
            MyLogger.logMethodEntry("toString", this, (Object[]) null, new Class<?>[0]);
            StringJoiner stringJoiner = new StringJoiner(", ", "{ customers = [", "] }");
            customers.forEach(customer -> stringJoiner.add(customer.toString()));
            var returnValue =  stringJoiner.toString();
            MyLogger.logMethodExit("toString", this, String.class, returnValue, (Object[]) null, new Class<?>[0]);
            return returnValue;
      }

      // metodo statico:
      public static int sum(Integer num1, Integer num2) {
            MyLogger.logStaticMethodEntry(MyList.class.getName(), "sum", new Object[] { num1, num2 },
                        new Class<?>[] { Integer.class, Integer.class });
            var returnValue = num1 + num2;
            MyLogger.logStaticMethodExit(MyList.class.getName(), "sum", int.class, returnValue,
                        new Object[] { num1, num2 }, new Class<?>[] { Integer.class, Integer.class });
            return returnValue;
      }

      private <T> Boolean performSetOperation(String methodName, Customer person, Predicate<Customer> operation) {
            MyLogger.logMethodEntry(methodName, this, new Object[] { person }, new Class<?>[] { Customer.class });
            var result = operation.test(person);
            MyLogger.logMethodExit(methodName, this, Boolean.class, result, new Object[] { person },
                        new Class<?>[] { Customer.class });
            return result;
      }

      public List<Customer> getCustomers() {
            MyLogger.logMethodEntry("getCustomers", this, (Object[]) null, new Class<?>[0]);
            MyLogger.logMethodExit("getCustomers", this, List.class, customers, (Object[]) null, new Class<?>[0]);
            return customers;
      }

      public void setCustomers(List<Customer> customers) {
            MyLogger.logMethodEntry("setCustomers", this, new Object[] { customers }, new Class<?>[] { Set.class });
            this.customers = customers;
            MyLogger.logMethodExit("setCustomers", this, void.class, null, new Object[] { customers },
                        new Class<?>[] { Set.class });
      }
}