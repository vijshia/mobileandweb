package com.kone.app.tests.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import com.kone.app.pages.salesforce.LoginPage;
import com.kone.app.pages.salesforce.MainPage;
import com.kone.framework.context.WebContext;
import com.kone.framework.utility.Log;
import ru.yandex.qatools.allure.annotations.Step;



public class SalesforceBaseTest {
	
	protected static WebDriver wdriver;
	
	private static Properties appData;
	private static Properties testData;
	private By successApproveText=By.xpath("//*[text()='Task successfully attached.']");
	private By dd_SalesLogout=By.xpath("//*[@id='globalHeaderNameMink']/*[@class='zen-selectArrow']");
	private By lnk_SalesLogout=By.xpath("//*[text()='Logout']");
	
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
	
	@AfterTest(alwaysRun = true)
	public void logoutApplication() {
		
		Log.info("");
		Log.info("**************************** Web AfterTest Execution has been Started ****************************");
		wdriver.findElement(dd_SalesLogout).click();
		wdriver.findElement(lnk_SalesLogout).click();
		
		LoginPage loginpage = new LoginPage();
		Assert.assertTrue(loginpage.isDisplayed(), "Failed to logout SalesForce");
		wdriver.manage().deleteAllCookies();
		Log.info("**************************** Web AfterTest Execution has been Completed ****************************");
		Log.info("");
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
