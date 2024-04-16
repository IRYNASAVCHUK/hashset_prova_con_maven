package com.example.hashset;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.logger.MyLogger;
import com.example.record.MyRecordEntering;
import com.example.record.MyRecordExiting;
import com.example.utils.Constants;

public class Counter {
    private static final Logger logger = MyLogger.getLogger();
    private int val;

    public void reset() {
        logger.logp(Level.INFO, getClass().getName(), "reset", Constants.ENTRY,
                new MyRecordEntering(null, this));
        val = 0;
        logger.logp(Level.INFO, getClass().getName(), "reset", Constants.RETURN,
                new MyRecordExiting<>(void.class, null, null, this));

    }

    public void inc() {
        logger.logp(Level.INFO, getClass().getName(), "inc", Constants.ENTRY,
        new MyRecordEntering(null, this));
        val++;
        logger.logp(Level.INFO, getClass().getName(), "inc", Constants.RETURN,
                new MyRecordExiting<>(void.class, null, null, this));
    }

    public int getValue() {
        logger.logp(Level.INFO, getClass().getName(), "getValue", Constants.ENTRY,
        new MyRecordEntering(null, this));
        logger.logp(Level.INFO, getClass().getName(), "getValue", Constants.RETURN,
        new MyRecordExiting<>(int.class, val, null, this));
        return val;
    }

    public void copy(Counter x) {
        logger.logp(Level.INFO, getClass().getName(), "copy", Constants.ENTRY,
        new MyRecordEntering(new Object[]{x}, this));
        val = x.val;
        logger.logp(Level.INFO, getClass().getName(), "copy", Constants.RETURN,
        new MyRecordExiting<>(void.class, null, new Object[]{x}, this));
    }
}
