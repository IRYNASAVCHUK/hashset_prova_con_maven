package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.logger.MyRecordExiting;

import java.util.Objects;
import java.util.logging.Logger;

public class Customer {
    private static final Logger logger = MyLogger.getLogger();
    private int id;
    private String name;

    public Customer(int id, String name) {
        logger.entering(getClass().getName(), "Customer", new Object[] { id, name });
        this.id = id;
        this.name = name;
        MyRecordExiting<Customer> result = new MyRecordExiting<>(Customer.class, this, new Object[] { id, name }, this);
        logger.exiting(getClass().getName(), "Customer", result);
    }

    public int getId() {
        logger.entering(getClass().getName(), "getId",null);
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, id, null, this);
        logger.exiting(getClass().getName(), "getId", result);
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException();
        logger.entering(getClass().getName(), "setId", new Object[] { id });
        this.id = id;
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, new Object[] { id }, this);
        logger.exiting(getClass().getName(), "setId", result);
    }

    public String getName() {
        logger.entering(getClass().getName(), "getName",null);
        MyRecordExiting<String> result = new MyRecordExiting<>(String.class, name, null, this);
        logger.exiting(getClass().getName(), "getName", result);
        return name;
    }

    public void setName(String name) {
        logger.entering(getClass().getName(), "setName", new Object[] { name });
        this.name = name;
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, new Object[] { name }, this);
        logger.exiting(getClass().getName(), "setName", result);
    }

    @Override
    public boolean equals(Object o) {
        logger.entering(getClass().getName(), "equals", new Object[] { o });
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        boolean returnValue = id == customer.id && name == customer.name;
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { o }, this);
        logger.exiting(getClass().getName(), "equals", result);
        return returnValue;
    }

    @Override
    public int hashCode() {
        logger.entering(getClass().getName(), "hashCode",null);
        int returnValue = Objects.hash(id);
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, returnValue, null, this);
        logger.exiting(getClass().getName(), "hashCode", result);
        return returnValue;
    }

    @Override
    public String toString() {
        logger.entering(getClass().getName(), "toString",null);
        String returnValue = "(" + id + " , " + name + ")";
        MyRecordExiting<String> result = new MyRecordExiting<>(String.class, returnValue, null, this);
        logger.exiting(getClass().getName(), "toString", result);
        return returnValue;
    }
}
