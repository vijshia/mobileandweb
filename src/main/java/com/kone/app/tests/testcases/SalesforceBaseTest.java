package com.kone.app.tests.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.kone.framework.context.WebContext;
import com.kone.framework.utility.Log;
import ru.yandex.qatools.allure.annotations.Step;



public class SalesforceBaseTest {
	
	protected static WebDriver wdriver;
	
	private static Properties appData;
	private static Properties testData;
	
/*	protected String loginUser;
	protected String loginPassword;*/
	
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
	
	@BeforeSuite(alwaysRun = false)
	public void getDriver() {
		wdriver = WebContext.wdriver;
	}
	
	
	@AfterSuite(alwaysRun = true)
	public void closeWebdriver() {
		wdriver.close();
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
