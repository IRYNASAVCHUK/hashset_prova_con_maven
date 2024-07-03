package helper;

import core.logger.MyLogger;

public class C {
	C f1;
	C f2;

	public C(C f1, C f2) {
		MyLogger.logMethodEntry("init", this, new Object[] { f1, f2 }, new Class<?>[] { C.class, C.class });
		this.f1 = f1;
		this.f2 = f2;
		MyLogger.logMethodExit("init", this, C.class, null, new Object[] { f1, f2 },
				new Class<?>[] { C.class, C.class });
	}
}
