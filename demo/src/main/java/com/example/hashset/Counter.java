package com.example.hashset;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.logger.MyLogger;
import com.example.record.MyRecordEntering;
import com.example.record.MyRecordExiting;

public class Counter {
    private static final Logger logger = MyLogger.getLogger();
    private int val;

    public void reset() {
        logger.logp(Level.INFO, getClass().getName(), "reset", "ENTRY",
                new MyRecordEntering(null, this));
        val = 0;
        logger.logp(Level.INFO, getClass().getName(), "reset", "RETURN",
                new MyRecordExiting<>(void.class, null, new Object[]{val}, this));

    }

    public void inc() {
        logger.logp(Level.INFO, getClass().getName(), "inc", "ENTRY",
        new MyRecordEntering(null, this));
        val++;
        logger.logp(Level.INFO, getClass().getName(), "inc", "RETURN",
                new MyRecordExiting<>(void.class, null, null, this));
    }

    public int getValue() {
        logger.logp(Level.INFO, getClass().getName(), "getValue", "ENTRY",
        new MyRecordEntering(null, this));
        logger.logp(Level.INFO, getClass().getName(), "getValue", "RETURN",
        new MyRecordExiting<>(int.class, val, null, this));
        return val;


    }

    public void copy(Counter x) {
        logger.logp(Level.INFO, getClass().getName(), "copy", "ENTRY",
        new MyRecordEntering(new Object[]{x}, this));
        val = x.val;
        logger.logp(Level.INFO, getClass().getName(), "copy", "RETURN",
        new MyRecordExiting<>(void.class, null, new Object[]{x}, this));
    }
}
