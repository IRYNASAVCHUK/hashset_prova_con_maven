package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.record.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Customer {
    private static final Logger logger = MyLogger.getLogger();
    private int id;
    private String name;

    public Customer(int id, String name) {
        logger.logp(Level.INFO, getClass().getName(), "init", "ENTRY",
                new MyRecordEntering(new Object[] { id, name }, this));
        this.id = id;
        this.name = name;
        logger.logp(Level.INFO, getClass().getName(), "init", "RETURN",
                new MyRecordExiting<>(Customer.class, this, new Object[] { id, name }, this));
    }

    public int getId() {
        logger.logp(Level.INFO, getClass().getName(), "getId", "ENTRY",
                new MyRecordEntering(null, this));
        logger.logp(Level.INFO, getClass().getName(), "getId", "RETURN",
                new MyRecordExiting<>(int.class, id, null, this));
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException();
        logger.logp(Level.INFO, getClass().getName(), "setId", "ENTRY",
                new MyRecordEntering(new Object[] { id }, this));
        this.id = id;
        logger.logp(Level.INFO, getClass().getName(), "setId", "RETURN",
                new MyRecordExiting<>(void.class, null, new Object[] { id }, this));
    }

    public String getName() {
        logger.logp(Level.INFO, getClass().getName(), "getName", "ENTRY",
                new MyRecordEntering(null, this));
        logger.logp(Level.INFO, getClass().getName(), "getName", "RETURN",
                new MyRecordExiting<>(String.class, name, null, this));
        return name;
    }

    public void setName(String name) {
        logger.logp(Level.INFO, getClass().getName(), "setName", "ENTRY",
                new MyRecordEntering(new Object[] { name }, this));
        this.name = name;
        logger.logp(Level.INFO, getClass().getName(), "setName", "RETURN",
                new MyRecordExiting<>(void.class, null, new Object[] { name }, this));
    }

    @Override
    public boolean equals(Object o) {
        logger.logp(Level.INFO, getClass().getName(), "equals", "ENTRY",
                new MyRecordEntering(new Object[] { o }, this));
        if (this == o) {
            logger.logp(Level.INFO, getClass().getName(), "equals", "RETURN",
                    new MyRecordExiting<>(boolean.class, true, new Object[] { o }, this));
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            logger.logp(Level.INFO, getClass().getName(), "equals", "RETURN",
                    new MyRecordExiting<>(boolean.class, false, new Object[] { o }, this));
            return false;
        }
        Customer customer = (Customer) o;
        boolean returnValue = (id == customer.id && name == customer.name);
        logger.logp(Level.INFO, getClass().getName(), "equals", "RETURN",
                new MyRecordExiting<>(boolean.class, returnValue, new Object[] { o }, this));
        return returnValue;
    }

    @Override
    public int hashCode() {
        logger.logp(Level.INFO, getClass().getName(), "hashCode", "ENTRY",
                new MyRecordEntering(null, this));
        int returnValue = Objects.hash(id);
        logger.logp(Level.INFO, getClass().getName(), "hashCode", "RETURN",
                new MyRecordExiting<>(int.class, returnValue, null, this));
        return returnValue;
    }

    @Override
    public String toString() {
        logger.logp(Level.INFO, getClass().getName(), "toString", "ENTRY",
                new MyRecordEntering(null, this));
        String returnValue = "(" + id + " , " + name + ")";
        logger.logp(Level.INFO, getClass().getName(), "toString", "RETURN",
                new MyRecordExiting<>(String.class, returnValue, null, this));
        return returnValue;
    }
}
