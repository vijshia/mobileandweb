package com.kone.framework.listener;

import java.util.Arrays;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.kone.framework.context.TestContext;
import com.kone.framework.context.WebContext;
import com.kone.framework.utility.Log;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Attachment;

public class TestNGListener extends TestListenerAdapter   {
	
    @Attachment(value = "{0}", type = "image/png")
    public byte[] captureScreenshot(String desc, boolean captureOnWeb) {
    	
    	if(captureOnWeb) {
    		Log.info("Capture screenshot from Web.");
    		WebDriver wdriver = WebContext.wdriver;
    		return ((TakesScreenshot)wdriver).getScreenshotAs(OutputType.BYTES);
    	}
    	
    	Log.info("Capture screenshot from Phone");
    	AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>)TestContext.driver;
    	String previousContext = driver.getContext();
    	
    	// Switch to NATIVE_APP to capture screenshot if needed
    	if(!previousContext.equals("NATIVE_APP")) {
    		driver.context("NATIVE_APP");
        	byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        	driver.context(previousContext);
        	return screenshot;
    	} 
    	
    	return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);    	        
    }
    
    @Attachment(value = "{0}", type = "text/plain")
    public String getPageSource(String desc, boolean captureOnWeb) {
    	
    	if(captureOnWeb) {
    		Log.info("Get page source from Web");
    		WebDriver wdriver = WebContext.wdriver;
    		return wdriver.getPageSource();
    	}
    	
    	Log.info("Get page source from Phone");
    	AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>)TestContext.driver;    	
    	return driver.getPageSource();    	        
    }
    
    @Override
    public void onTestFailure(ITestResult iTestResult) {
    	
        if(Arrays.asList(iTestResult.getMethod().getGroups()).contains("web")) {
        	captureScreenshot("Screenshot on test failure", true);
        	getPageSource("Page source on test failure", true);
        } else {
        	captureScreenshot("Screenshot on test failure", false);
        	getPageSource("Page source on test failure", false);
        }
        
        String [] className = iTestResult.getTestClass().getName().split("\\.");
        Log.endTestCase(className[className.length - 1] +
		                "." + iTestResult.getMethod().getMethodName(), 
		                "Failed.");
    }
    
    @Override
    public void onTestStart(ITestResult iTestResult) {
    	String [] className = iTestResult.getTestClass().getName().split("\\.");
    	Log.startTestCase(className[className.length - 1] +
    			          "." + iTestResult.getMethod().getMethodName());
    	System.out.println("==TestNGListener_CLass==");
    }
    
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    	String [] className = iTestResult.getTestClass().getName().split("\\.");
    	Log.endTestCase(className[className.length - 1] +
		               "." + iTestResult.getMethod().getMethodName(),
    	               "Passed");
    }
    
    
}
