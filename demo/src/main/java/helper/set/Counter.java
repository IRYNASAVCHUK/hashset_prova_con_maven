package helper.set;

import core.logger.MyLogger;

public class Counter {
    private int val;

    public void reset() {
        MyLogger.logMethodEntry("reset", this, (Object[]) null, new Class<?>[0]);
        val = 0;
        MyLogger.logMethodExit("reset", this, void.class, null, (Object[]) null, new Class<?>[0]);
    }

    public void inc() {
        MyLogger.logMethodEntry("inc", this, (Object[]) null, new Class<?>[0]);
        val++;
        MyLogger.logMethodExit("inc", this, void.class, null, (Object[]) null, new Class<?>[0]);
    }

    public int getVal() {
        MyLogger.logMethodEntry("getVal", this, (Object[]) null, new Class<?>[0]);
        MyLogger.logMethodExit("getVal", this, int.class, val, (Object[]) null, new Class<?>[0]);
        return val;
    }

    public void setVal(int val) {
        MyLogger.logMethodEntry("setVal", this, new Object[] { val }, new Class<?>[] { int.class });
        this.val = val;
        MyLogger.logMethodExit("setVal", this, void.class, null, new Object[] { val }, new Class<?>[] { int.class });
    }

    public void copy(Counter x) {
        MyLogger.logMethodEntry("copy", this, new Object[] { x }, new Class<?>[] { Counter.class });
        val = x.val;
        MyLogger.logMethodExit("copy", this, void.class, null, new Object[] { x }, new Class<?>[] { Counter.class });
    }
}
