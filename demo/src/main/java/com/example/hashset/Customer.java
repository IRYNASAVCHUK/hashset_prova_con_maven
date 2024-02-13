package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.logger.MyRecord;

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
        MyRecord<Customer> result = new MyRecord<>(Customer.class, this, new Object[] { id, name });
        logger.exiting(getClass().getName(), "Customer", result);
    }

    public int getId() {
        logger.entering(getClass().getName(), "getId",null);
        MyRecord<Integer> result = new MyRecord<>(int.class, id, null);
        logger.exiting(getClass().getName(), "getId", result);
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException();
        logger.entering(getClass().getName(), "setId", new Object[] { id });
        this.id = id;
        MyRecord<Void> result = new MyRecord<>(void.class, null, new Object[] { id });
        logger.exiting(getClass().getName(), "setId", result);
    }

    public String getName() {
        logger.entering(getClass().getName(), "getName",null);
        MyRecord<String> result = new MyRecord<>(String.class, name, null);
        logger.exiting(getClass().getName(), "getName", result);
        return name;
    }

    public void setName(String name) {
        logger.entering(getClass().getName(), "setName", new Object[] { name });
        this.name = name;
        MyRecord<Void> result = new MyRecord<>(void.class, null, new Object[] { name });
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
        MyRecord<Boolean> result = new MyRecord<>(boolean.class, returnValue, new Object[] { o });
        logger.exiting(getClass().getName(), "equals", result);
        return returnValue;
    }

    @Override
    public int hashCode() {
        logger.entering(getClass().getName(), "hashCode",null);
        int returnValue = Objects.hash(id);
        MyRecord<Integer> result = new MyRecord<>(int.class, returnValue, null);
        logger.exiting(getClass().getName(), "hashCode", result);
        return returnValue;
    }

    @Override
    public String toString() {
        logger.entering(getClass().getName(), "toString",null);
        String returnValue = "(" + id + " , " + name + ")";
        MyRecord<String> result = new MyRecord<>(String.class, returnValue, null);
        logger.exiting(getClass().getName(), "toString", result);
        return returnValue;
    }
}
