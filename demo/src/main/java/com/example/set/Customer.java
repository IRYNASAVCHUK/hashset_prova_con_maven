package com.example.set;

import com.example.logger.MyLogger;

import java.util.Objects;

public class Customer {
        private int id;
        private String name;

        public Customer(int id, String name) {
                MyLogger.logMethodEntry("init", this, new Object[] { id, name });
                this.id = id;
                this.name = name;
                MyLogger.logMethodExit("init", this, Customer.class, this, new Object[] { id, name });
        }

        public int getId() {
                MyLogger.logMethodEntry("getId", this, (Object[]) null);
                MyLogger.logMethodExit("getId", this, int.class, id, (Object[]) null);
                return id;
        }

        public void setId(int id) {
                if (id < 0)
                        throw new IllegalArgumentException();
                MyLogger.logMethodEntry("setId", this, (new Object[] { id }));
                this.id = id;
                MyLogger.logMethodExit("setId", this, void.class, null, new Object[] { id });
        }

        public String getName() {
                MyLogger.logMethodEntry("getName", this, (Object[]) null);
                MyLogger.logMethodExit("getName", this, String.class, name, (Object[]) null);
                return name;
        }

        public void setName(String name) {
                MyLogger.logMethodEntry("setName", this, new Object[] { name });
                this.name = name;
                MyLogger.logMethodExit("setName", this, void.class, null, new Object[] { name });
        }

        @Override
        public boolean equals(Object o) {
                MyLogger.logMethodEntry("equals", this, new Object[] { o });
                if (this == o) {
                        MyLogger.logMethodExit("equals", this, boolean.class, true, new Object[] { o });
                        return true;
                }
                if (o == null || getClass() != o.getClass()) {
                        MyLogger.logMethodExit("equals", this, boolean.class, false, new Object[] { o });
                        return false;
                }
                Customer customer = (Customer) o;
                boolean returnValue = (id == customer.id && name == customer.name);
                MyLogger.logMethodExit("equals", this, boolean.class, returnValue, new Object[] { o });
                return returnValue;
        }

        @Override
        public int hashCode() {
                MyLogger.logMethodEntry("hashCode", this, (Object[]) null);
                int returnValue = Objects.hash(id);
                MyLogger.logMethodExit("hashCode", this, int.class, returnValue, (Object[]) null);
                return returnValue;
        }

        @Override
        public String toString() {
                MyLogger.logMethodEntry("toString", this, (Object[]) null);
                String returnValue = "(" + id + " , " + name + ")";
                MyLogger.logMethodExit("toString", this, String.class, returnValue, (Object[]) null);
                return returnValue;
        }
}
