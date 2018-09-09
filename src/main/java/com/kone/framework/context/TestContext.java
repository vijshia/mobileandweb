package com.kone.framework.context;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.kone.framework.appium.AppiumServer;
import com.kone.framework.utility.Log;
import com.kone.framework.utility.PropertiesLoader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestContext {
	
	public static TestContext instance = new TestContext();	
	public static AppiumDriver<MobileElement> driver;
	private static final String APPIUM_CAP_PREFIX = "appium.capabilities";
	
	public TestContext() {		
	}
	
	private static String getCapVal(String capName, String defVal) {
		return PropertiesLoader.instance.getProperty(capName, defVal);
	}
		
	static {
		
		// Start local appium server if server url is not localhost
		if(getCapVal("appium.server.url", "127.0.0.1").equals("127.0.0.1")) {
			AppiumServer.sever.startServer();
		}		
		
		Log.info("Creating appium driver:");
		
		DesiredCapabilities caps = new DesiredCapabilities();
		
		Enumeration<?> e = PropertiesLoader.instance.propertyNames();
		
		for (;e.hasMoreElements();) {
			
			String property = e.nextElement().toString();
			
			if(property.contains(APPIUM_CAP_PREFIX)) {
				
				String[] elements = property.split("\\.");
				String capName = elements[elements.length - 1];
				String capValue = PropertiesLoader.instance.getProperty(property);
				
				if(property.contains("chromedriverExecutable")) {
				       if(getCapVal("appium.server.url", "127.0.0.1").equals("127.0.0.1")) {
					    capValue = System.getProperty("user.dir") + capValue;
                                      }
				}
				
				Log.info("    -> Set appium desired capability " + capName +
						 "=" + capValue);
				caps.setCapability(capName, capValue);
			}
		}

    	
    	String port = getCapVal("appium.server.port", "4723");
    	String serverUrl = getCapVal("appium.server.url", "127.0.0.1");
    	
    	try {
			driver = new AndroidDriver<MobileElement>(
					           new URL("http://" + serverUrl + ":" + port + "/wd/hub"), caps);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
    	
    	Log.info("    -> Appium driver is created successfully!");
	}

}
