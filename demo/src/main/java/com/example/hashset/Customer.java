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
    
    public long getId() {
        logger.entering(getClass().getName(), "getId");
        logger.exiting(getClass().getName(), "getId",id);
        return id;
    }

    public void setId(long id) {
        if (id < 0)
            throw new IllegalArgumentException();
        logger.entering(getClass().getName(), "setId", id);
        this.id = id;
        logger.exiting(getClass().getName(), "setId", Void.class);
    }

    public String getName() {
        logger.entering(getClass().getName(), "getName");
        logger.exiting(getClass().getName(), "getName",name);
        return name;
    }

    public void setName(String name) {
        logger.entering(getClass().getName(), "setName", name);
        this.name = name;
        logger.exiting(getClass().getName(), "setName",Void.class);
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
