package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.logger.MyRecordEntering;
import com.example.logger.MyRecordExiting;

import java.util.Objects;
import java.util.logging.Logger;

public class Customer {
    private static final Logger logger = MyLogger.getLogger();
    private int id;
    private String name;

    public Customer(int id, String name) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { id, name }, this, false);
        logger.entering(getClass().getName(), "Customer", enter);
        this.id = id;
        this.name = name;
        MyRecordExiting<Customer> result = new MyRecordExiting<>(Customer.class, this, new Object[] { id, name }, this, false);
        logger.exiting(getClass().getName(), "Customer", result);
    }

    public int getId() {
        MyRecordEntering enter = new MyRecordEntering(null, this, false);
        logger.entering(getClass().getName(), "getId",enter);
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, id, null, this, false);
        logger.exiting(getClass().getName(), "getId", result);
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException();
        MyRecordEntering enter = new MyRecordEntering(new Object[] { id }, this, false);
        logger.entering(getClass().getName(), "setId",enter );
        this.id = id;
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, new Object[] { id }, this, false);
        logger.exiting(getClass().getName(), "setId", result);
    }

    public String getName() {
        MyRecordEntering enter = new MyRecordEntering(null, this, false);
        logger.entering(getClass().getName(), "getName",enter);
        MyRecordExiting<String> result = new MyRecordExiting<>(String.class, name, null, this, false);
        logger.exiting(getClass().getName(), "getName", result);
        return name;
    }

    public void setName(String name) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { name }, this, false);
        logger.entering(getClass().getName(), "setName", enter);
        this.name = name;
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, new Object[] { name }, this, false);
        logger.exiting(getClass().getName(), "setName", result);
    }

    @Override
    public boolean equals(Object o) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { o }, this, false);
        logger.entering(getClass().getName(), "equals", enter);
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        boolean returnValue = id == customer.id && name == customer.name;
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { o }, this, false);
        logger.exiting(getClass().getName(), "equals", result);
        return returnValue;
    }

    @Override
    public int hashCode() {
        MyRecordEntering enter = new MyRecordEntering(null, this, false);
        logger.entering(getClass().getName(), "hashCode",enter);
        int returnValue = Objects.hash(id);
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, returnValue, null, this, false);
        logger.exiting(getClass().getName(), "hashCode", result);
        return returnValue;
    }

    @Override
    public String toString() {
        MyRecordEntering enter = new MyRecordEntering(null, this, false);
        logger.entering(getClass().getName(), "toString",enter);
        String returnValue = "(" + id + " , " + name + ")";
        MyRecordExiting<String> result = new MyRecordExiting<>(String.class, returnValue, null, this, false);
        logger.exiting(getClass().getName(), "toString", result);
        return returnValue;
    }
}
