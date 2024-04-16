package com.example.hashset;

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
                logger.logp(Level.INFO, getClass().getName(), "init", Constants.ENTRY,
                                new MyRecordEntering(new Object[] { id, name }, this));
                this.id = id;
                this.name = name;
                logger.logp(Level.INFO, getClass().getName(), "init", Constants.RETURN,
                                new MyRecordExiting<>(Customer.class, this, new Object[] { id, name }, this));
        }

        public int getId() {
                logger.logp(Level.INFO, getClass().getName(), "getId", Constants.ENTRY,
                                new MyRecordEntering(null, this));
                logger.logp(Level.INFO, getClass().getName(), "getId", Constants.RETURN,
                                new MyRecordExiting<>(int.class, id, null, this));
                return id;
        }

        public void setId(int id) {
                if (id < 0)
                        throw new IllegalArgumentException();
                logger.logp(Level.INFO, getClass().getName(), "setId", Constants.ENTRY,
                                new MyRecordEntering(new Object[] { id }, this));
                this.id = id;
                logger.logp(Level.INFO, getClass().getName(), "setId", Constants.RETURN,
                                new MyRecordExiting<>(void.class, null, new Object[] { id }, this));
        }

        public String getName() {
                logger.logp(Level.INFO, getClass().getName(), "getName", Constants.ENTRY,
                                new MyRecordEntering(null, this));
                logger.logp(Level.INFO, getClass().getName(), "getName", Constants.RETURN,
                                new MyRecordExiting<>(String.class, name, null, this));
                return name;
        }

        public void setName(String name) {
                logger.logp(Level.INFO, getClass().getName(), "setName", Constants.ENTRY,
                                new MyRecordEntering(new Object[] { name }, this));// log ()//record.getMessage()  //file di config // fare meno ripetitivo// prenderr nome pre o post
                this.name = name;
                logger.logp(Level.INFO, getClass().getName(), "setName", Constants.RETURN,
                                new MyRecordExiting<>(void.class, null, new Object[] { name }, this));
        }

        @Override
        public boolean equals(Object o) {
                logger.logp(Level.INFO, getClass().getName(), "equals", Constants.ENTRY,
                                new MyRecordEntering(new Object[] { o }, this));
                if (this == o) {
                        logger.logp(Level.INFO, getClass().getName(), "equals", Constants.RETURN,
                                        new MyRecordExiting<>(boolean.class, true, new Object[] { o }, this));
                        return true;
                }
                if (o == null || getClass() != o.getClass()) {
                        logger.logp(Level.INFO, getClass().getName(), "equals", Constants.RETURN,
                                        new MyRecordExiting<>(boolean.class, false, new Object[] { o }, this));
                        return false;
                }
                Customer customer = (Customer) o;
                boolean returnValue = (id == customer.id && name == customer.name);
                logger.logp(Level.INFO, getClass().getName(), "equals", Constants.RETURN,
                                new MyRecordExiting<>(boolean.class, returnValue, new Object[] { o }, this));
                return returnValue;
        }

        @Override
        public int hashCode() {
                logger.logp(Level.INFO, getClass().getName(), "hashCode", Constants.ENTRY,
                                new MyRecordEntering(null, this));
                int returnValue = Objects.hash(id);
                logger.logp(Level.INFO, getClass().getName(), "hashCode", Constants.RETURN,
                                new MyRecordExiting<>(int.class, returnValue, null, this));
                return returnValue;
        }

        @Override
        public String toString() {
                logger.logp(Level.INFO, getClass().getName(), "toString", Constants.ENTRY,
                                new MyRecordEntering(null, this));
                String returnValue = "(" + id + " , " + name + ")";
                logger.logp(Level.INFO, getClass().getName(), "toString", Constants.RETURN,
                                new MyRecordExiting<>(String.class, returnValue, null, this));
                return returnValue;
        }
}
