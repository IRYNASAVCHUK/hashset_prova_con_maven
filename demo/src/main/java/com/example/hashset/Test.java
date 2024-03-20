package com.example.hashset;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.example.logger.MyLogger;

public class Test {
	private static final Logger logger = MyLogger.getLogger();
	private int id;

	public Test() {
		LogRecord lr = new LogRecord(Level.ALL, "entering Test()\n");
		this.id = 24;
		lr.setParameters(new Object[] { 1 });
		logger.log(lr);
		lr = new LogRecord(Level.ALL, "exiting Test()\n");
		lr.setParameters(new Object[] { 1 });
		logger.log(lr);
	}

	public Test m() {
		LogRecord lr = new LogRecord(Level.ALL, "entering m()\n");
		lr.setParameters(new Object[] { 1 });
		logger.log(lr);
		lr = new LogRecord(Level.ALL, "exiting m()\n");
		lr.setParameters(new Object[] { 1 });
		logger.log(lr);
		return this;
	}
	
	public int getId() {
		LogRecord lr = new LogRecord(Level.ALL, "entering getId()\n");
		lr.setParameters(new Object[] { 1 });
		logger.log(lr);
		lr = new LogRecord(Level.ALL, "exiting getId()\n");
		lr.setParameters(new Object[] { 1 });
		logger.log(lr);
		return this.id;
	}
	
	public static void st() {
		LogRecord lr = new LogRecord(Level.ALL, "entering st()\n");
		lr.setParameters(new Object[] { 1 });
		logger.log(lr);
		lr = new LogRecord(Level.ALL, "exiting st()\n");
		lr.setParameters(new Object[] { 1 });
		logger.log(lr);
	}
}
