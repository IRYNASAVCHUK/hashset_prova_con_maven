package com.example.set;

import com.example.logger.MyLogger;
import com.example.record.*;
import com.example.utils.Constants;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {
        private static final Logger logger = MyLogger.getLogger();
        private int id;
        private String name;

        public Customer(int id, String name) {
                logMethodEntry("init", new Object[] { id, name });
                this.id = id;
                this.name = name;
                logger.logp(Level.INFO, getClass().getName(), "init", Constants.RETURN,
                                new MyRecordExiting<>(Customer.class, this, new Object[] { id, name }, this));
        }

        public int getId() {
                logMethodEntry("getId", (Object[]) null);
                logMethodExit("getId", int.class, id, (Object[]) null);
                return id;
        }

        public void setId(int id) {
                if (id < 0)
                        throw new IllegalArgumentException();
                logMethodEntry("setId", (new Object[] { id }));
                this.id = id;
                logMethodExit("setId", void.class, null, new Object[] { id });
        }

        public String getName() {
                logMethodEntry("getName", (Object[]) null);
                logMethodExit("getName", String.class, name, (Object[]) null);
                return name;
        }

        public void setName(String name) {
                logMethodEntry("setName", new Object[] { name });
                this.name = name;
                logMethodExit("setName", void.class, null, new Object[] { name });
        }

        @Override
        public boolean equals(Object o) {
                logMethodEntry("equals", new Object[] { o });
                if (this == o) {
                        logMethodExit("equals", boolean.class, true, new Object[] { o });
                        return true;
                }
                if (o == null || getClass() != o.getClass()) {
                        logMethodExit("equals", boolean.class, false, new Object[] { o });
                        return false;
                }
                Customer customer = (Customer) o;
                boolean returnValue = (id == customer.id && name == customer.name);
                logMethodExit("equals", boolean.class, returnValue, new Object[] { o });
                return returnValue;
        }

        @Override
        public int hashCode() {
                logMethodEntry("hashCode", (Object[]) null);
                int returnValue = Objects.hash(id);
                logMethodExit("hashCode",int.class, returnValue,  (Object[]) null);
                return returnValue;
        }

        @Override
        public String toString() {
                logMethodEntry("toString", (Object[]) null);
                String returnValue = "(" + id + " , " + name + ")";
                logMethodExit( "toString", String.class, returnValue, (Object[]) null);
                return returnValue;
        }

        private void logMethodEntry(String methodName, Object... args) {
                logger.logp(Level.INFO, getClass().getName(), methodName, Constants.ENTRY,
                                new MyRecordEntering(args, this));
        }

        private <T> void logMethodExit(String methodName, Class<T> returnType, T result, Object... args) {
                logger.logp(Level.INFO, getClass().getName(), methodName, Constants.RETURN,
                                new MyRecordExiting<>(returnType, result, args, this));
        }
}
