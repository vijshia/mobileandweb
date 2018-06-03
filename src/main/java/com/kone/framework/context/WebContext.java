package com.kone.framework.context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.kone.framework.utility.Log;
import com.kone.framework.utility.PropertiesLoader;

public class WebContext {
	
	public static WebContext instance = new WebContext();
	public static WebDriver wdriver;
	
	public WebContext() {		
	}
	
	private static String getCapVal(String capName, String defVal) {
		return PropertiesLoader.instance.getProperty(capName, defVal);
	}
		
	static {
		// Start browser
		Log.info("Creating web driver.");
		String browserType = getCapVal("selenium.browser", "None");
		if(browserType.equals("firefox")) {
			wdriver = new FirefoxDriver();
			Log.info("Created Firefox driver.");
		} else if(browserType.equals("chrome")){
			wdriver = new ChromeDriver();
			Log.info("Created Chrome driver.");
		}
	}

}
