package com.example.set;

import com.example.logger.MyLogger;

public class Counter {
    private int val;

    public void reset() {
        MyLogger.logMethodEntry("reset", this, (Object[]) null);
        val = 0;
        MyLogger.logMethodExit("reset", this, void.class, null, (Object[]) null);
    }

    public void inc() {
        MyLogger.logMethodEntry("inc", this, (Object[]) null);
        val++;
        MyLogger.logMethodExit("inc", this, void.class, null, (Object[]) null);
    }

    public int getVal() {
        MyLogger.logMethodEntry("getVal", this, (Object[]) null);
        MyLogger.logMethodExit("getVal", this, int.class, val, (Object[]) null);
        return val;
    }

    public void setVal(int val) {
        MyLogger.logMethodEntry("setVal", this, new Object[]{val});
        this.val = val;
        MyLogger.logMethodExit("setVal", this, void.class, null, new Object[]{val});
    }

    public void copy(Counter x) {
        MyLogger.logMethodEntry("copy", this, new Object[] { x });
        val = x.val;
        MyLogger.logMethodExit("copy", this, void.class, null, new Object[] { x });
    }
}
