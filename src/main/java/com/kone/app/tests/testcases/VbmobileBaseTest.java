package com.kone.app.tests.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.kone.app.pages.vbmobile.SideMenuPage;
import com.kone.app.pages.vbmobile.SurveyManagerPage;
import com.kone.framework.appium.AppiumServer;
import com.kone.framework.context.TestContext;
import com.kone.framework.utility.Log;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;


public class VbmobileBaseTest {
	
	protected static AndroidDriver<MobileElement> driver;
	
	private static Properties appData;
	private By menuButton = By.xpath("//*[@aria-label='Menu']");
	private By logoutMenu = By.xpath("//*[text()='Logout']");
	
	static {
		
		appData = new Properties();
		
		try {
			appData.load(new FileInputStream(new File("properties/config.properties")));
		} catch (IOException e) {
			Log.error("There was a problem reading 'properties/config.properties'."
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
	
	
	@AfterTest(alwaysRun = true)
	public void logoutApplication() {
		
		Log.info("-----AfterTest-----");
		/*driver.findElement(menuButton).click();
    	Log.info("Wait for element present: " + logoutMenu + " / With Timeout: " + 50);
    	WebDriverWait wait = new WebDriverWait(driver, 50);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(logoutMenu));
    	Log.info("Wait executed");
		driver.findElement(logoutMenu).click();
		Log.info("Logout clicked in mobile from AfterTest");*/
		
		SurveyManagerPage surveyManagerScreen = new SurveyManagerPage();
		surveyManagerScreen.openSideMenu("Logout");
		Log.info("OPENSIDE MENU in AfterTest in Mobile");
		SideMenuPage sideMenupage = new SideMenuPage();
		sideMenupage.logout();
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
