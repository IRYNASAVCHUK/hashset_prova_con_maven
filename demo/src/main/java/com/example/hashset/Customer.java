package com.example.hashset;

import com.example.logger.MyLogger;
import com.example.record.*;

import java.util.*;
import java.util.logging.Logger;

public class Customer {
    private static final Logger logger = MyLogger.getLogger();
    private int id;
    private String name;

    public Customer(int id, String name) {
        if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
            logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { id, name }, this)));
            this.id = id;
            this.name = name;
            logger.log(
                    MyLogger.logExiting(new MyRecordExiting<>(Customer.class, this, new Object[] { id, name }, this)));
        } else {
            this.id = id;
            this.name = name;
        }
    }

    public int getId() {
        if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
            logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
            logger.log(MyLogger.logExiting(new MyRecordExiting<>(int.class, id, null, this)));
        }
        return id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException();
        if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
            logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { id }, this)));
            this.id = id;
            logger.log(MyLogger.logExiting(new MyRecordExiting<>(void.class, null, new Object[] { id }, this)));
        } else
            this.id = id;
    }

    public String getName() {
        if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
            logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
            logger.log(MyLogger.logExiting(new MyRecordExiting<>(String.class, name, null, this)));
        }
        return name;
    }

    public void setName(String name) {
        if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
            logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { name }, this)));
            this.name = name;
            logger.log(MyLogger.logExiting(new MyRecordExiting<>(void.class, null, new Object[] { name }, this)));
        } else
            this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
            logger.log(MyLogger.logEntering(new MyRecordEntering(new Object[] { o }, this)));
            if (this == o) {
                logger.log(MyLogger.logExiting(new MyRecordExiting<>(boolean.class, true, new Object[] { o }, this)));
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                logger.log(MyLogger.logExiting(new MyRecordExiting<>(boolean.class, false, new Object[] { o }, this)));
                return false;
            }
            Customer customer = (Customer) o;
            boolean returnValue = (id == customer.id && name == customer.name);
            logger.log(
                    MyLogger.logExiting(new MyRecordExiting<>(boolean.class, returnValue, new Object[] { o }, this)));
            return returnValue;
        }
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        return id == customer.id && name == customer.name;
    }

    @Override
    public int hashCode() {
        if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
            logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
            int returnValue = Objects.hash(id);
            logger.log(MyLogger.logExiting(new MyRecordExiting<>(int.class, returnValue, null, this)));
            return returnValue;
        }
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        if ("main".equals(getCallerMethodName(Thread.currentThread().getStackTrace()))) {
            logger.log(MyLogger.logEntering(new MyRecordEntering(null, this)));
            String returnValue = "(" + id + " , " + name + ")";
            logger.log(MyLogger.logExiting(new MyRecordExiting<>(String.class, returnValue, null, this)));
            return returnValue;
        }
        return "(" + id + " , " + name + ")";
    }

    private String getCallerMethodName(StackTraceElement[] stackTrace) {
        // Analizza gli elementi dello stack trace per ottenere il chiamante diretto
        String callerMethodName = Arrays.stream(stackTrace)
              // Ignora il primo elemento dello stack trace (quello relativo al metodo stesso)
              .skip(1)
              // Trova il primo elemento non relativo alla classe corrente
              .filter(element -> !element.getClassName().equals(getClass().getName()))
              // Ottieni il nome del metodo chiamante
              .map(StackTraceElement::getMethodName)
              // Trova il primo nome di metodo non standard (non inizia con 'lambda$' o '$$')
              .filter(name -> !name.startsWith("lambda$") && !name.contains("$$"))
              .findFirst()
              .orElse("Unknown");
        return callerMethodName;
     }
}
