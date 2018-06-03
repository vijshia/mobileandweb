package com.kone.framework.utility;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log {
	
	private static Logger Log = Logger.getLogger(Log.class);
	private final static String LOG_PATTERN_LAYOUT = "%d{yyyy-MMM-dd HH:mm:ss} %p - %m%n";
	
	static {
		BasicConfigurator.configure(new ConsoleAppender(
		           new PatternLayout(LOG_PATTERN_LAYOUT)));
	}
	
	public static void startTestCase(String sTestCaseName){
		
		Log.info("");
		Log.info("**************************** Starts Test Case: \"" +
							sTestCaseName + "\"");
	}
		 
	public static void endTestCase(String sTestCaseName, String result){
	 
		Log.info("**************************** Ends Test Case: \"" +
							sTestCaseName + "\" - Result: " + result);
		Log.info("");
	 }
 
	 public static void info(String message) {
	 
		 Log.info(message);	 
	}
		 
	 public static void warn(String message) {
		 
		 Log.warn(message);		 
	 }
		 
	 public static void error(String message) {
		 
		 Log.error(message);		 
	 }
		 
	 public static void fatal(String message) {
		 
		 Log.fatal(message);		 
	 }
		 
	 public static void debug(String message) {
		 
		 Log.debug(message);		 
	 }
}
