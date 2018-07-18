package com.kone.app.pages.vbmobile;

import java.util.List;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.kone.app.pages.PhoneBasePage;
import com.kone.framework.context.TestContext;
import com.kone.framework.utility.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class RetriveSurveyIDtoLinkPage extends PhoneBasePage{
	
public static AppiumDriver<MobileElement> driver;
	
	private By getSurveyData = By.xpath("//*[@class='spinner']/..//div/p/span");
	private By header_History = By.xpath("(//*[text()='History'])[last()]");
	
	public RetriveSurveyIDtoLinkPage() {
		driver = (AndroidDriver<MobileElement>)TestContext.driver;
	}
	
	public void RetriveSurveyIDtoLink() {
		
		List<MobileElement> retrivesurveydatas = gettingMobileElementsfromList(getSurveyData);
		for(MobileElement retrivesurveydata: retrivesurveydatas) {
//			retrivesurveydata.getText();
			Log.info(retrivesurveydata.getText());
		}

		Assert.assertTrue(isDisplayed(DEFAULT_WAIT_ELEMENT_TIMEOUT), "Failed to retrive data");
	}
	
	
	public boolean isDisplayed(long timeout) {
		return waitForElementPresent(header_History, timeout) != null;
	}

}
