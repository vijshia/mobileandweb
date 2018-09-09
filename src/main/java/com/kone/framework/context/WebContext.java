package com.kone.framework.context;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException; 
import java.net.URL;

import com.kone.framework.utility.Log;
import com.kone.framework.utility.PropertiesLoader;

public class WebContext {
	
	public static WebContext instance = new WebContext();
	public static WebDriver wdriver;
	public static DesiredCapabilities chromeCapabilities;
	
	public WebContext() {		
	}
	
	private static String getCapVal(String capName, String defVal) {
		return PropertiesLoader.instance.getProperty(capName, defVal);
	}
		
	static {
		
		// Start browser based on selenium.browser setting in properties file
		Log.info("Creating web driver.");
		String browserType = getCapVal("selenium.browser", "None");
		
		if(browserType.equals("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", 
					           System.getProperty("user.dir") + "/drivers/geckodriver");
			wdriver = new FirefoxDriver();
			Log.info("Created Firefox driver.");
			
		} else if(browserType.equals("chrome")){
			
			System.setProperty("webdriver.chrome.driver", 
			                   System.getProperty("user.dir") + "/drivers/chromedriver");
			chromeCapabilities = DesiredCapabilities.chrome();
			
			if(getCapVal("selenium.remote.chromedriver", "localhost").equals("localhost")) {
				wdriver = new ChromeDriver();
			} else {
				try {
					wdriver = new RemoteWebDriver(new URL("http://" 
				                                           + getCapVal("selenium.remote.chromedriver", "localhost") 
					                                       + ":" 
				                                           + getCapVal("selenium.remote.chromeport", "4444")
							                               + "/wd/hub"),
							                      chromeCapabilities);
				} catch(MalformedURLException e) {}
			}
			
			Log.info("Created Chrome driver.");
		}
	}

}
