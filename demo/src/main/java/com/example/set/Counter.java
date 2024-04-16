package com.example.set;

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
        logMethodEntry("reset", (Object[]) null);
        val = 0;
        logMethodExit("reset", void.class, null, (Object[]) null);
    }

    public void inc() {
        logMethodEntry("inc", (Object[]) null);
        val++;
        logMethodExit("inc", void.class, null, (Object[]) null);
    }

    public int getValue() {
        logMethodEntry("getValue", (Object[]) null);
        logMethodExit("getValue", int.class, val, (Object[]) null);
        return val;
    }

    public void copy(Counter x) {
        logMethodEntry("copy", new Object[] { x });
        val = x.val;
        logMethodExit("copy", void.class, null, new Object[] { x });
    }

    private void logMethodEntry(String methodName, Object... args) {
        logger.logp(Level.INFO, getClass().getName(), methodName, Constants.ENTRY,
                new MyRecordEntering(args, this));
    }

    private <T> void logMethodExit(String methodName, Class<T> returnType, T result, Object... args) {
        logger.logp(Level.INFO, getClass().getName(), methodName, Constants.RETURN,
                new MyRecordExiting<>(returnType, result, args, this));
    }
}
