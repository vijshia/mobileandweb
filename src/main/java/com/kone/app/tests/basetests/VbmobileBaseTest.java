package com.kone.app.tests.basetests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.kone.framework.appium.AppiumServer;
import com.kone.framework.context.TestContext;
import com.kone.framework.utility.Log;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;


public class VbmobileBaseTest {
	
	protected static AndroidDriver<MobileElement> driver;
	
	private static Properties appData;
	private static Properties testData;
	
	static {
		appData = new Properties();
		testData = new Properties();
		
		try {
			appData.load(new FileInputStream(new File("properties/config.properties")));
		} catch (IOException e) {
			Log.error("There was a problem reading 'properties/config.properties'."
					+ " Please make sure it exists!");
		}
		
		try {
			testData.load(new FileInputStream(new File("properties/testdata/testdata.properties")));
		} catch (IOException e) {
			Log.error("There was a problem reading 'properties/testdata/testData.properties'."
					+ " Please make sure it exists!");
		}
	}
	
	@BeforeSuite(alwaysRun = true)
	public void getDriver() throws InterruptedException {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
		driver.context("WEBVIEW_com.kone.vbmobile.debug");
		Log.info("Context is switched to: " + driver.getContext());
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void stopApiumServer() {
		AppiumServer.sever.stopServer();
	}
	
	@Step("Waiting for {0} millisecond(s)")
    public void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
