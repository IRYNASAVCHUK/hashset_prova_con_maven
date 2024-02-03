package com.example.hashset;

import java.util.logging.Logger;
import com.example.logger.MyLogger;

public class Customer {
    private static final Logger logger = MyLogger.getLogger();
    private long id;
    private String name;

    public Customer(long id, String name) {
        logger.entering(getClass().getName(), "Customer", new Object[] { id, name });
        this.id = id;
        this.name = name;
        logger.exiting(getClass().getName(), "Customer", this);
    }
    public Customer(long id) {
        this(id, "");
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        return id == customer.id&&name==customer.name;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
     @Override
    public String toString() {
        return "("+id + " , " + name+")";
    }

}
