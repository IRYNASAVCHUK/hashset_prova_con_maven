package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.record.*;

import java.util.Objects;
import java.util.logging.Logger;

public class Customer {
    private static final Logger logger = MyLogger.getLogger();
    private int id;
    private String name;

    public Customer(int id, String name) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { id, name }, this);
        logger.log(MyLogger.logEntering(enter));
        this.id = id;
        this.name = name;
        MyRecordExiting<Customer> result = new MyRecordExiting<>(Customer.class, this, new Object[] { id, name }, this);
        logger.log(MyLogger.logExiting(result));
    }

    public int getId() {
        MyRecordEntering enter = new MyRecordEntering(null, this);
        logger.log(MyLogger.logEntering(enter));
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, id, null, this);
        logger.log(MyLogger.logExiting(result));
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException();
        MyRecordEntering enter = new MyRecordEntering(new Object[] { id }, this);
        logger.log(MyLogger.logEntering(enter));
        this.id = id;
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, new Object[] { id }, this);
        logger.log(MyLogger.logExiting(result));
    }

    public String getName() {
        MyRecordEntering enter = new MyRecordEntering(null, this);
        logger.log(MyLogger.logEntering(enter));
        MyRecordExiting<String> result = new MyRecordExiting<>(String.class, name, null, this);
        logger.log(MyLogger.logExiting(result));
        return name;
    }

    public void setName(String name) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { name }, this);
        logger.log(MyLogger.logEntering(enter));
        this.name = name;
        MyRecordExiting<Void> result = new MyRecordExiting<>(void.class, null, new Object[] { name }, this);
        logger.log(MyLogger.logExiting(result));
    }

    @Override
    public boolean equals(Object o) {
        MyRecordEntering enter = new MyRecordEntering(new Object[] { o }, this);
        logger.log(MyLogger.logEntering(enter));
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        boolean returnValue = id == customer.id && name == customer.name;
        MyRecordExiting<Boolean> result = new MyRecordExiting<>(boolean.class, returnValue, new Object[] { o }, this);
        logger.log(MyLogger.logExiting(result));
        return returnValue;
    }

    @Override
    public int hashCode() {
        MyRecordEntering enter = new MyRecordEntering(null, this);
        logger.log(MyLogger.logEntering(enter));
        int returnValue = Objects.hash(id);
        MyRecordExiting<Integer> result = new MyRecordExiting<>(int.class, returnValue, null, this);
        logger.log(MyLogger.logExiting(result));
        return returnValue;
    }

    @Override
    public String toString() {
        MyRecordEntering enter = new MyRecordEntering(null, this);
        logger.log(MyLogger.logEntering(enter));
        String returnValue = "(" + id + " , " + name + ")";
        MyRecordExiting<String> result = new MyRecordExiting<>(String.class, returnValue, null, this);
        logger.log(MyLogger.logExiting(result));
        return returnValue;
    }
}
