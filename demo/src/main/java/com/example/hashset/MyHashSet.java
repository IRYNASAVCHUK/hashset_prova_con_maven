package com.example.hashset;

import java.util.*;
import java.util.logging.Logger;

import com.example.logger.MyLogger;
import com.example.logger.MyResult;

public class MyHashSet {
    private static final Logger logger = MyLogger.getLogger();
    Set<Customer> customers;

    public MyHashSet() {
        //logger.entering(getClass().getName(), "MyHashSet");
        this.customers = new HashSet<>();
        logger.exiting(getClass().getName(), "MyHashSet", this);

    }

    public boolean addCustomer(Customer persone) {
        //logger.entering(getClass().getName(), "addCustomer", new Object[] { persone });
        boolean returnValue = customers.add(persone);
        logger.exiting(getClass().getName(), "addCustomer", new Object[] { returnValue, persone });
        return returnValue;
    }

    public boolean removeCustomer(Customer persone) {
       // logger.entering(getClass().getName(), "removeCustomer", new Object[] { persone });
        boolean returnValue = customers.remove(persone);
        logger.exiting(getClass().getName(), "removeCustomer", new Object[] { returnValue, persone });
        return returnValue;
    }

    public boolean containsCustomer(Customer persone) {
       // logger.entering(getClass().getName(), "containsCustomer", new Object[] { persone });
        boolean returnValue = customers.contains(persone);
        logger.exiting(getClass().getName(), "containsCustomer", new Object[] { returnValue, persone });
        return returnValue;
    }

    public int getSize() {
        //logger.entering(getClass().getName(), "getSize");
        int returnValue = customers.size();
        logger.exiting(getClass().getName(), "getSize", returnValue);
        return returnValue;

    }

    public void clearSet() {
        //logger.entering(getClass().getName(), "clearSet");
        customers.clear();
        logger.exiting(getClass().getName(), "clearSet", Void.class);
        //logger.exiting(getClass().getName(), "clearSet", Void.class); isVoid true
    }

    @Override
    public String toString() {
        return "{" + "customers=" + customers + '}';
    }
}
//TODO:
// tipi, super tipi del altro
// Enum Logger undefined
// oggetto con due campi: result e params object[]... e terzo isVoid=true/false

// java record(Oracal da leggere)

// recod semplifica molto tutto
// record(params)
// record(result, params)
// !!! a provare: implementa interface comune (getResult di default???) o usare instanceof (se il risultato oppure no)
// getter 
// instanceof di tipo giusto