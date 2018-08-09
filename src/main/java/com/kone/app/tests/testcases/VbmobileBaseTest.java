package com.kone.app.tests.testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import com.kone.app.pages.vbmobile.LoginPage;
import com.kone.app.pages.vbmobile.SideMenuPage;
import com.kone.app.pages.vbmobile.SurveyManagerPage;
import com.kone.framework.appium.AppiumServer;
import com.kone.framework.context.TestContext;
import com.kone.framework.utility.ExcelReader;
import com.kone.framework.utility.Log;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import ru.yandex.qatools.allure.annotations.Step;
import static com.kone.app.tests.factories.BaseFactory.TEST_DATA_EXCEL_PATH;
import static com.kone.app.tests.factories.BaseFactory.LOGIN_DATA_SHEET;
import static com.kone.app.pages.PhoneBasePage.DEFAULT_WAIT_PAGE_DISPLAY_TIMEOUT;

public class VbmobileBaseTest {
	
	protected static AndroidDriver<MobileElement> driver;
	
	private static Properties appData;
	private ExcelReader excelreader;
	
	protected String Excel_ValuetoStore;
	protected String salesForceenvironment;
	
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
/*		Set<String> contextNames = driver.getContextHandles();
	     for (String contextName : contextNames) {
	    	 Log.info(contextName);
	     }		*/
		excelreader = new ExcelReader(TEST_DATA_EXCEL_PATH);
		try {
			for (Entry<String, String> entry : excelreader.GetData(LOGIN_DATA_SHEET).entrySet()) {
				String key = entry.getKey();
				Excel_ValuetoStore = entry.getValue();
				if (key.equals("SF_Environment")) {
					salesForceenvironment = Excel_ValuetoStore;	
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Unable to Get Environment Details");
		}
			if(salesForceenvironment.equalsIgnoreCase("FULL")) {
				driver.context("WEBVIEW_com.kone.vbmobileTrain"); //for Training APP
				Log.info("Context is switched to: " + driver.getContext()+" (Full SandBox)");
			} else if(salesForceenvironment.equalsIgnoreCase("QA")) {
				driver.context("WEBVIEW_com.kone.vbmobile.debug"); //for Acceptance APP
				Log.info("Context is switched to: " + driver.getContext()+" (QA SandBox)");
			}
	}
	
	@AfterSuite(alwaysRun = true)
	public void stopApiumServer() {
		
		AppiumServer.sever.stopServer();
	}
	
	
	@AfterTest(alwaysRun = true)
	public void logoutApplication() {
		
		Log.info("");
		Log.info("**************************** Mobile AfterTest Execution has been Started ****************************");
		SurveyManagerPage surveyManagerScreen = new SurveyManagerPage();
		surveyManagerScreen.openSideMenu("Logout");

		SideMenuPage sideMenupage = new SideMenuPage();
		sideMenupage.logout();

		driver.resetApp();
	
		Set<String> contextNames = driver.getContextHandles();
	     for (String contextName : contextNames) {
	    	 Log.info(contextName);
	     }
		
	     LoginPage loginScreen = new LoginPage();
	     Assert.assertTrue(loginScreen.isDisplayed(180), "Failed to logout");
	     
		Log.info("**************************** Mobile AfterTest Execution has been Completed ****************************");
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
