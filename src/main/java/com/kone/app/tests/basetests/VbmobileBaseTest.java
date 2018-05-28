package com.kone.app.tests.basetests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.kone.framework.appium.AppiumServer;
import com.kone.framework.context.TestContext;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class VbmobileBaseTest {
	
	protected static AndroidDriver<MobileElement> driver;
	
	private static Properties appData;
	private static Properties testData;
	
	protected String loginUser;
	protected String loginPassword;
	
	static {
		appData = new Properties();
		testData = new Properties();
		
		try {
			appData.load(new FileInputStream(new File("properties/config.properties")));
		} catch (IOException e) {
			System.err.println("There was a problem reading 'properties/config.properties'. Please make sure it exists!");
		}
		
		try {
			testData.load(new FileInputStream(new File("properties/testdata/testdata.properties")));
		} catch (IOException e) {
			System.err.println("There was a problem reading 'properties/testdata/testData.properties'. Please make sure it exists!");
		}
	}
	
	@BeforeSuite(alwaysRun = true)
	public void getDriver() throws InterruptedException {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
		Thread.sleep(10000);
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
	
//	@Step("Ensure that we are logged out")
//	protected void ensureLoggedOut() {
//		
//		LoginScreen loginScreen = new LoginScreen();
//		MainScreen menuScreen = new MainScreen();
//		
//		driver.closeApp();
//		driver.launchApp();
//		
//		if(loginScreen.isDisplayed()) {
//			System.out.println("We are on the correct login screen");
//			return;
//		}
//		
//		menuScreen.openSettings().openAccountInfo().signOut();
//	}
//	
//	@Step("Ensure that we are logged in")
//	protected void ensureLoggedIn(String username, String password) {
//		LoginScreen loginScreen = new LoginScreen();
//		AllNotesScreen allNotesScreen = new AllNotesScreen();
//		
//		driver.closeApp();
//		driver.launchApp();
//		
//		if(allNotesScreen.isDisplayed()) {
//			System.out.println("We are on the correct logged in screen");
//			return;
//		}
//		
//		loginScreen.signIn(username, password);
//	}
}
