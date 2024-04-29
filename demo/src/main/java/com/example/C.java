package com.example;

import com.example.logger.MyLogger;

class C {
	C f1;
	C f2;

	C(C f1, C f2) {
		MyLogger.logMethodEntry("init", this, new Object[]{f1, f2}, new Class<?>[]{C.class, C.class} );
		this.f1 = f1;
		this.f2 = f2;
		MyLogger.logMethodExit("init", this,C.class, null,  new Object[]{f1, f2}, new Class<?>[]{C.class, C.class});
	}

}
