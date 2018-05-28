package com.kone.framework.context;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.kone.framework.appium.AppiumServer;
import com.kone.framework.properties.PropertiesLoader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestContext {
	
	public static TestContext instance = new TestContext();	
	public static AppiumDriver<MobileElement> driver;
	
	public TestContext() {		
	}
	
	private static String getCapVal(String capName, String defVal) {
		return PropertiesLoader.instance.getProperty(capName, defVal);
	}
		
	static {
		try {
			AppiumServer.sever.startServer();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Creating driver.");

    	DesiredCapabilities caps = new DesiredCapabilities();
    	caps.setCapability(MobileCapabilityType.DEVICE_NAME, 
    			           getCapVal("appium.capabilities.deviceName",
    			        		     "My Phone"));
    	caps.setCapability(MobileCapabilityType.UDID,
    			           getCapVal("appium.capabilities.udid",
	        		                 "None"));
    	caps.setCapability(MobileCapabilityType.PLATFORM_NAME,
		                   getCapVal("appium.capabilities.platformName",
  		                             "Android"));
    	caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                           getCapVal("appium.capabilities.platformVersion",
                                     "5.0"));
    	caps.setCapability("appPackage", 
                           getCapVal("appium.capabilities.android.appPackage",
                                     "None"));
    	caps.setCapability("appActivity",
                           getCapVal("appium.capabilities.android.appActivity",
                                     "None"));
    	caps.setCapability("noReset", 
                           getCapVal("appium.capabilities.noReset",
                                     "None"));
    	caps.setCapability("automationName", 
    			           getCapVal("appium.capabilities.android.automationName",
                                     "None"));
    	caps.setCapability("chromedriverExecutable", 
    					   getCapVal("appium.capabilities.chromedriverExecutable",
                                      "None"));
    	
    	String port = getCapVal("appium.port", "4723");
    	String serverUrl = getCapVal("appium.server.url", "127.0.0.1");
    	
    	try {
			driver = new AndroidDriver<MobileElement>(new URL("http://" 
    	                                                               + serverUrl 
    	                                                               + ":"
    	                                                               + port 
    	                                                               + "/wd/hub"), 
					                                           caps);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
